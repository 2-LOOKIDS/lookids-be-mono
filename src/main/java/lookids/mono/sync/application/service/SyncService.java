package lookids.mono.sync.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.comment.application.port.in.CommentLogUseCase;
import lookids.mono.batch.favorite.application.port.in.FavoriteLogUseCase;
import lookids.mono.batch.feed.application.port.in.FeedLogUseCase;
import lookids.mono.batch.follow.application.port.in.FollowLogUseCase;
import lookids.mono.chatting.application.UserKafkaListener;
import lookids.mono.commentread.application.port.in.CommentDeleteUseCase;
import lookids.mono.commentread.application.port.in.CommentReadCreateUseCase;
import lookids.mono.commentread.application.port.in.UserProfileUpdateUseCase;
import lookids.mono.elasticsearch.application.SearchService;
import lookids.mono.favorite.application.FavoriteKafkaListener;
import lookids.mono.feedread.application.FeedKafkaListener;
import lookids.mono.followblock.block.application.BlockService;
import lookids.mono.followblock.follow.application.FollowKafkaListener;
import lookids.mono.map.application.MapService;
import lookids.mono.notification.service.NotificationKafkaListener;
import lookids.mono.subscribe.service.SubscribeKafkaListener;
import lookids.mono.sync.application.mapper.SyncDtoMapper;
import lookids.mono.sync.application.port.dto.ChatDto;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.FavoriteDto;
import lookids.mono.sync.application.port.dto.FeedDeleteDto;
import lookids.mono.sync.application.port.dto.FeedDto;
import lookids.mono.sync.application.port.dto.FollowDto;
import lookids.mono.sync.application.port.dto.PetProfileDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;
import lookids.mono.sync.application.port.dto.UserProfileDto;
import lookids.mono.sync.application.port.in.SyncServicePort;
import lookids.mono.user.petprofile.application.PetProfileKafkaListener;
import lookids.mono.user.userprofile.application.UserProfileKafkaListener;

@Slf4j
@RequiredArgsConstructor
@Service
public class SyncService implements SyncServicePort {

	private final SyncDtoMapper syncDtoMapper;
	private final UserKafkaListener userKafkaListener;

	private final CommentLogUseCase commentLogUseCase;

	private final CommentReadCreateUseCase commentReadCreateUseCase;
	private final UserProfileUpdateUseCase userProfileUpdateUseCase;
	private final CommentDeleteUseCase commentDeleteUseCase;

	private final FavoriteLogUseCase favoriteLogUseCase;
	private final FeedLogUseCase feedLogUseCase;
	private final FollowLogUseCase followLogUseCase;

	//private final FeedReadService feedReadService;
	private final FeedKafkaListener feedKafkaListener;

	private final BlockService blockService;

	//private final FollowService followService;
	private final FollowKafkaListener followKafkaListener;

	private final FavoriteKafkaListener favoriteKafkaListener;

	private final MapService mapService;

	private final SearchService searchService;

	// private final UserProfileService userProfileService;
	// private final PetProfileService petProfileService;
	private final UserProfileKafkaListener userProfileKafkaListener;
	private final PetProfileKafkaListener petProfileKafkaListener;

	private final NotificationKafkaListener notificationKafkaListener;

	private final SubscribeKafkaListener subscribeKafkaListener;

	@Override
	public void userDelete(UserDeleteDto userDeleteDto) {
		userKafkaListener.userDelete(syncDtoMapper.toUserKafkaRequestDto(userDeleteDto));
		searchService.consumeUserDelete(syncDtoMapper.toKafkaUserDeleteRequestDto(userDeleteDto));
		feedKafkaListener.accountDeleteConsume(syncDtoMapper.toUuidKafkaDto(userDeleteDto));
	}

	@Override
	public void createComment(CommentDto commentDto) {
		UserProfileDto userProfileDto = syncDtoMapper.toUserProfileDto(
			userProfileKafkaListener.consumeCommentEvent(commentDto.getUuid()));
		commentReadCreateUseCase.createCommentRead(
			syncDtoMapper.toCommentCreateEventDto(userProfileDto, commentDto));
		commentLogUseCase.commentCreateLog(syncDtoMapper.toCommentCreateBatchDto(commentDto));
		notificationKafkaListener.consumeCommentNotificationEvent(
			syncDtoMapper.toNotificationCommentRequestDto(commentDto));
	}

	@Override
	public void createReply(ReplyDto replyDto) {
		UserProfileDto userProfileDto = syncDtoMapper.toUserProfileDto(
			userProfileKafkaListener.consumeCommentEvent(replyDto.getUuid()));
		commentReadCreateUseCase.createReplyRead(syncDtoMapper.toReplyCreateEventDto(userProfileDto, replyDto));
		commentLogUseCase.replyCreateLog(syncDtoMapper.toReplyCreateBatchDto(replyDto));
		notificationKafkaListener.consumeCommentReplyNotificationEvent(
			syncDtoMapper.toNotificationCommentReplyRequestDto(replyDto));
	}

	@Override
	public void deleteComment(CommentDto commentDto) {
		commentDeleteUseCase.deleteComment(syncDtoMapper.toCommentDeleteDto(commentDto));
		commentLogUseCase.commentDeleteLog(syncDtoMapper.toCommentCreateBatchDto(commentDto));
	}

	@Override
	public void deleteReply(ReplyDto replyDto) {
		commentDeleteUseCase.deleteReply(syncDtoMapper.toReplyDeleteDto(replyDto));
		commentLogUseCase.replyDeleteLog(syncDtoMapper.toReplyCreateBatchDto(replyDto));
	}

	@Override
	public void createChatMessage(ChatDto chatDto) {
		notificationKafkaListener.consumeChattingNotificationEvent(
			syncDtoMapper.toNotificationChattingRequestDto(chatDto));
	}

	@Override
	public void createUserProfile(UserProfileDto userProfileDto) {
		searchService.consumeUserCreate(syncDtoMapper.toKafkaUserCreateDto(userProfileDto));
	}

	@Override
	public void updateUserProfileImage(UserProfileDto userProfileDto) {
		userProfileUpdateUseCase.updateProfileImage(syncDtoMapper.toUserProfileImageDto(userProfileDto));
		searchService.consumeUserImageUpdate(syncDtoMapper.toKafkaUserImageUpdateRequestDto(userProfileDto));
		followKafkaListener.consumeUserImageUpdate(syncDtoMapper.toKafkaUserUpdateRequestDto(userProfileDto));
		feedKafkaListener.imageUpdateConsume(syncDtoMapper.toUserImageKafkaDto(userProfileDto));
	}

	@Override
	public void updateUserProfileNickname(UserProfileDto userProfileDto) {
		userProfileUpdateUseCase.updateNickname(syncDtoMapper.toUserProfileNicknameDto(userProfileDto));
		searchService.consumeUserNicknameUpdate(syncDtoMapper.toUserNicknameUpdateRequestDto(userProfileDto));
		followKafkaListener.consumeUserNicknameUpdate(syncDtoMapper.toKafkaUserUpdateRequestDto(userProfileDto));
		feedKafkaListener.nickNameUpdateConsume(syncDtoMapper.toUserNickNameKafkaDto(userProfileDto));
	}

	@Override
	public void createFavorite(FavoriteDto favoriteDto) {
		favoriteLogUseCase.favoriteUpdate(syncDtoMapper.toFavoriteUpdateEventDto(favoriteDto));
		if ("FEED".equals(favoriteDto.getFavoriteType())) {
			notificationKafkaListener.consumeFeedFavoriteNotificationEvent(
				syncDtoMapper.toNotificationFavoriteRequestDto(favoriteDto));
		} else {
			notificationKafkaListener.consumeCommentFavoriteNotificationEvent(
				syncDtoMapper.toNotificationFavoriteRequestDto(favoriteDto));
		}
	}

	@Override
	public void updateFavorite(FavoriteDto favoriteDto) {
		favoriteLogUseCase.favoriteUpdate(syncDtoMapper.toFavoriteUpdateEventDto(favoriteDto));
	}

	@Override
	public void createFeed(FeedDto feedDto) {
		UserProfileDto userProfileDto = syncDtoMapper.toUserProfileDto(
			userProfileKafkaListener.consumeCommentEvent(feedDto.getUuid()));
		feedKafkaListener.feedConsume(syncDtoMapper.toFeedKafkaDto(feedDto),
			syncDtoMapper.toUserKafkaDto(userProfileDto));
		log.info("feedDto:{}", feedDto);
		feedLogUseCase.feedCreateLog(syncDtoMapper.toFeedCreateEventDto(feedDto));
		mapService.consumeFeedCreate(syncDtoMapper.toFeedCodeResponseDto(feedDto));
		searchService.consumeFeedCreate(syncDtoMapper.toKafkaFeedCreateRequestDto(feedDto));
		notificationKafkaListener.consumeFeedNotificationEvent(syncDtoMapper.toNotificationFeedRequestDto(
			syncDtoMapper.toNotificationDto(
				subscribeKafkaListener.consumeFeedEvent(syncDtoMapper.toFeedKafkaRequestDto(feedDto)))));
	}

	@Override
	public void deleteFeed(FeedDeleteDto feedDeleteDto) {
		feedLogUseCase.feedDeleteLog(syncDtoMapper.toFeedDeleteEventDto(feedDeleteDto));
		feedKafkaListener.feedDeleteConsume(syncDtoMapper.toFeedDeleteKafkaDto(feedDeleteDto));
		mapService.consumeFeedDelete(feedDeleteDto.getFeedCode());
		searchService.consumeFeedDelete(feedDeleteDto.getFeedCode());
	}

	@Override
	public void createFollow(FollowDto followDto) {
		followLogUseCase.followCreateLog(syncDtoMapper.toFollowEventDto(followDto));
		notificationKafkaListener.consumeFollowNotificationEvent(
			syncDtoMapper.toNotificationFollowRequestDto(followDto));
		followKafkaListener.consumeFollowInfo(syncDtoMapper.toKafkaFollowDto(
			syncDtoMapper.toFollowProfileDto(
				userProfileKafkaListener.consumeFollowEvent(followDto.getSenderUuid(), followDto.getReceiverUuid()))));

	}

	@Override
	public void deleteFollow(FollowDto followDto) {
		followLogUseCase.followDeleteLog(syncDtoMapper.toFollowEventDto(followDto));
	}

	@Override
	public void createPetProfile(PetProfileDto petProfileDto) {
		searchService.consumePetCreate(syncDtoMapper.toKafkaPetCreateRequestDto(petProfileDto));
	}

	@Override
	public void updatePetProfile(PetProfileDto petProfileDto) {
		searchService.consumePetUpdate(syncDtoMapper.toKafkaPetUpdateRequestDto(petProfileDto));
		feedKafkaListener.petProfileUpdateConsume(syncDtoMapper.toPetImageKafkaDto(petProfileDto));
	}

	@Override
	public void deletePetProfile(String petCode) {
		searchService.consumePetDelete(syncDtoMapper.toKafkaPetDeleteRequestDto(petCode));
	}

	@Override
	public String readImageByPetCode(String petCode) {
		return petProfileKafkaListener.findPetImage(petCode);
	}

	@Override
	public List<String> getFollowUuidList(String uuid) {
		return followKafkaListener.consumeForFollowUuid(uuid);
	}

	@Override
	public List<String> getBlockUuidList(String uuid) {
		return blockService.blockListRequest(uuid);
	}

	@Override
	public List<String> getFavoriteFeedCodes(String uuid) {
		return favoriteKafkaListener.consumeFeed(uuid);
	}
}
