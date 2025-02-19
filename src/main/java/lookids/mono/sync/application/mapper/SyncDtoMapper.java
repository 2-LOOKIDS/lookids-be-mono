package lookids.mono.sync.application.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.auth.dto.in.AccountDeleteKafkaRequestDto;
import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteUpdateEventDto;
import lookids.mono.batch.feed.application.port.dto.FeedCreateEventDto;
import lookids.mono.batch.feed.application.port.dto.FeedDeleteEventDto;
import lookids.mono.batch.follow.application.port.dto.FollowEventDto;
import lookids.mono.chatting.dto.in.UserKafkaRequestDto;
import lookids.mono.chatting.dto.out.NotificationKafkaRequestDto;
import lookids.mono.comment.vo.out.CommentKafkaVo;
import lookids.mono.comment.vo.out.ReplyKafkaVo;
import lookids.mono.commentread.application.port.dto.CommentCreateEventDto;
import lookids.mono.commentread.application.port.dto.CommentDeleteDto;
import lookids.mono.commentread.application.port.dto.ReplyCreateEventDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;
import lookids.mono.commentread.application.port.dto.UserProfileImageDto;
import lookids.mono.commentread.application.port.dto.UserProfileNicknameDto;
import lookids.mono.elasticsearch.dto.in.KafkaFeedCreateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserCreateDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserImageUpdateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserNicknameUpdateRequestDto;
import lookids.mono.favorite.dto.FavoriteRequestDto;
import lookids.mono.feed.dto.in.DeleteKafkaDto;
import lookids.mono.feed.dto.in.FeedKafkaDto;
import lookids.mono.feedread.dto.in.FeedDeleteKafkaDto;
import lookids.mono.feedread.dto.in.FeedReadKafkaDto;
import lookids.mono.feedread.dto.in.UserImageKafkaDto;
import lookids.mono.feedread.dto.in.UserKafkaDto;
import lookids.mono.feedread.dto.in.UserNickNameKafkaDto;
import lookids.mono.feedread.dto.in.UuidKafkaDto;
import lookids.mono.followblock.follow.dto.in.KafkaFollowDto;
import lookids.mono.followblock.follow.dto.in.KafkaUserUpdateRequestDto;
import lookids.mono.followblock.follow.dto.out.KafkaAlarmFollowResponseDto;
import lookids.mono.map.dto.out.FeedCodeResponseDto;
import lookids.mono.notification.dto.in.NotificationChattingRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentReplyRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentRequestDto;
import lookids.mono.notification.dto.in.NotificationFavoriteRequestDto;
import lookids.mono.notification.dto.in.NotificationFeedRequestDto;
import lookids.mono.notification.dto.in.NotificationFollowRequestDto;
import lookids.mono.subscribe.dto.in.FeedKafkaRequestDto;
import lookids.mono.subscribe.dto.in.NotificationRequestDto;
import lookids.mono.sync.application.port.dto.ChatDto;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.FavoriteDto;
import lookids.mono.sync.application.port.dto.FeedDeleteDto;
import lookids.mono.sync.application.port.dto.FeedDto;
import lookids.mono.sync.application.port.dto.FollowDto;
import lookids.mono.sync.application.port.dto.FollowProfileDto;
import lookids.mono.sync.application.port.dto.NotificationDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;
import lookids.mono.sync.application.port.dto.UserProfileDto;
import lookids.mono.user.userprofile.dto.out.FollowKafkaDto;
import lookids.mono.user.userprofile.dto.out.UserProfileKafkaDto;

@Component
public class SyncDtoMapper {
	public UserDeleteDto toUserDeleteDto(AccountDeleteKafkaRequestDto accountDeleteKafkaRequestDto) {
		return UserDeleteDto.builder().uuid(accountDeleteKafkaRequestDto.getUuid()).build();
	}

	public UserKafkaRequestDto toUserKafkaRequestDto(UserDeleteDto userDeleteDto) {
		return UserKafkaRequestDto.builder().uuid(userDeleteDto.getUuid()).build();
	}

	public CommentDto toCommentDto(CommentKafkaVo commentKafkaVo) {
		return CommentDto.builder()
			.commentCode(commentKafkaVo.getCommentCode())
			.feedCode(commentKafkaVo.getFeedCode())
			.feedUuid(commentKafkaVo.getFeedUuid())
			.content(commentKafkaVo.getContent())
			.createdAt(commentKafkaVo.getCreatedAt())
			.uuid(commentKafkaVo.getUuid())
			.build();
	}

	public ReplyDto toReplyDto(ReplyKafkaVo replyKafkaVo) {
		return ReplyDto.builder()
			.commentCode(replyKafkaVo.getCommentCode())
			.feedCode(replyKafkaVo.getFeedCode())
			.feedUuid(replyKafkaVo.getFeedUuid())
			.content(replyKafkaVo.getContent())
			.createdAt(replyKafkaVo.getCreatedAt())
			.uuid(replyKafkaVo.getUuid())
			.parentCommentCode(replyKafkaVo.getParentCommentCode())
			.build();
	}

	public UserProfileDto toUserProfileDto(UserProfileKafkaDto userProfileKafkaDto) {
		return UserProfileDto.builder()
			.userUuid(userProfileKafkaDto.getUserUuid())
			.nickname(userProfileKafkaDto.getNickname())
			.tag(userProfileKafkaDto.getTag())
			.image(userProfileKafkaDto.getImage())
			.build();
	}

	public CommentCreateEventDto toCommentCreateEventDto(UserProfileDto userProfileDto,
		CommentDto commentDto) {
		return CommentCreateEventDto.builder()
			.commentCode(commentDto.getCommentCode())
			.feedCode(commentDto.getFeedCode())
			.userUuid(commentDto.getUuid())
			.content(commentDto.getContent())
			.createdAt(commentDto.getCreatedAt())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.image(userProfileDto.getImage())
			.build();
	}

	public ReplyCreateEventDto toReplyCreateEventDto(UserProfileDto userProfileDto,
		ReplyDto replyDto) {
		return ReplyCreateEventDto.builder()
			.commentCode(replyDto.getCommentCode())
			.parentCommentCode(replyDto.getParentCommentCode())
			.userUuid(replyDto.getUuid())
			.content(replyDto.getContent())
			.createdAt(replyDto.getCreatedAt())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.image(userProfileDto.getImage())
			.build();
	}

	public CommentCreateBatchDto toCommentCreateBatchDto(CommentDto commentDto) {
		return CommentCreateBatchDto.builder()
			.commentCode(commentDto.getCommentCode())
			.feedCode(commentDto.getFeedCode())
			.uuid(commentDto.getUuid())
			.createdAt(commentDto.getCreatedAt())
			.build();
	}

	public ReplyCreateBatchDto toReplyCreateBatchDto(ReplyDto replyDto) {
		return ReplyCreateBatchDto.builder()
			.commentCode(replyDto.getCommentCode())
			.feedCode(replyDto.getFeedCode())
			.uuid(replyDto.getUuid())
			.createdAt(replyDto.getCreatedAt())
			.parentCommentCode(replyDto.getParentCommentCode())
			.build();
	}

	public CommentDeleteDto toCommentDeleteDto(CommentDto commentDto) {
		return CommentDeleteDto.builder()
			.commentCode(commentDto.getCommentCode())
			.feedCode(commentDto.getFeedCode())
			.build();
	}

	public ReplyDeleteDto toReplyDeleteDto(ReplyDto replyDto) {
		return ReplyDeleteDto.builder()
			.commentCode(replyDto.getCommentCode())
			.parentCommentCode(replyDto.getParentCommentCode())
			.build();
	}

	public NotificationChattingRequestDto toNotificationChattingRequestDto(ChatDto chatDto) {
		return NotificationChattingRequestDto.builder()
			.senderUuid(chatDto.getSenderUuid())
			.receiverUuidList(chatDto.getReceiverUuidList())
			.roomId(chatDto.getRoomId())
			.content(chatDto.getContent())
			.mediaUrl(chatDto.getMediaUrl())
			.build();
	}

	public ChatDto toChatDto(NotificationKafkaRequestDto notificationKafkaRequestDto) {
		return ChatDto.builder()
			.senderUuid(notificationKafkaRequestDto.getSenderUuid())
			.receiverUuidList(notificationKafkaRequestDto.getReceiverUuidList())
			.roomId(notificationKafkaRequestDto.getRoomId())
			.content(notificationKafkaRequestDto.getContent())
			.mediaUrl(notificationKafkaRequestDto.getMediaUrl())
			.build();
	}

	public KafkaUserDeleteRequestDto toKafkaUserDeleteRequestDto(UserDeleteDto userDeleteDto) {
		return KafkaUserDeleteRequestDto.builder().uuid(userDeleteDto.getUuid()).build();
	}

	public KafkaUserCreateDto toKafkaUserCreateDto(UserProfileDto userProfileDto) {
		return KafkaUserCreateDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.image(userProfileDto.getImage())
			.build();
	}

	public UserProfileImageDto toUserProfileImageDto(UserProfileDto userProfileDto) {
		return UserProfileImageDto.builder()
			.userUuid(userProfileDto.getUserUuid())
			.image(userProfileDto.getImage())
			.build();
	}

	public KafkaUserImageUpdateRequestDto toKafkaUserImageUpdateRequestDto(UserProfileDto userProfileDto) {
		return KafkaUserImageUpdateRequestDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.image(userProfileDto.getImage())
			.build();
	}

	public UserProfileNicknameDto toUserProfileNicknameDto(UserProfileDto userProfileDto) {
		return UserProfileNicknameDto.builder()
			.userUuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.build();
	}

	public KafkaUserNicknameUpdateRequestDto toUserNicknameUpdateRequestDto(UserProfileDto userProfileDto) {
		return KafkaUserNicknameUpdateRequestDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.build();
	}

	public NotificationCommentRequestDto toNotificationCommentRequestDto(CommentDto commentDto) {
		return NotificationCommentRequestDto.builder()
			.feedCode(commentDto.getFeedCode())
			.uuid(commentDto.getUuid())
			.content(commentDto.getContent())
			.build();
	}

	public NotificationCommentReplyRequestDto toNotificationCommentReplyRequestDto(ReplyDto replyDto) {
		return NotificationCommentReplyRequestDto.builder()
			.feedCode(replyDto.getFeedCode())
			.uuid(replyDto.getUuid())
			.content(replyDto.getContent())
			.build();
	}

	public FavoriteDto toFavoriteDto(FavoriteRequestDto favoriteRequestDto) {
		return FavoriteDto.builder()
			.uuid(favoriteRequestDto.getUuid())
			.receiverUuid(favoriteRequestDto.getAuthorUuid())
			.targetCode(favoriteRequestDto.getTargetCode())
			.favoriteState(favoriteRequestDto.getFavoriteState())
			.favoriteType(favoriteRequestDto.getFavoriteType().toString())
			.build();
	}

	public FavoriteUpdateEventDto toFavoriteUpdateEventDto(FavoriteDto favoriteDto) {
		return FavoriteUpdateEventDto.builder()
			.uuid(favoriteDto.getUuid())
			.targetCode(favoriteDto.getTargetCode())
			.favoriteState(favoriteDto.getFavoriteState())
			.favoriteType(favoriteDto.getFavoriteType())
			.build();
	}

	public NotificationFavoriteRequestDto toNotificationFavoriteRequestDto(FavoriteDto favoriteDto) {
		return NotificationFavoriteRequestDto.builder()
			.senderUuid(favoriteDto.getUuid())
			.receiverUuid(favoriteDto.getReceiverUuid())
			.feedCode(favoriteDto.getTargetCode())
			.type(favoriteDto.getFavoriteType())
			.build();
	}

	public FeedDto toFeedDto(FeedKafkaDto feedKafkaDto) {
		return FeedDto.builder()
			.feedCode(feedKafkaDto.getFeedCode())
			.uuid(feedKafkaDto.getUuid())
			.petCode(feedKafkaDto.getPetCode())
			.content(feedKafkaDto.getContent())
			.tagList(feedKafkaDto.getTagList())
			.state(feedKafkaDto.isState())
			.mediaUrlList(feedKafkaDto.getMediaUrlList())
			.createdAt(feedKafkaDto.getCreatedAt())
			.build();
	}

	public FeedDeleteDto toFeedDeleteDto(DeleteKafkaDto deleteKafkaDto) {
		return FeedDeleteDto.builder()
			.feedCode(deleteKafkaDto.getFeedCode())
			.uuid(deleteKafkaDto.getUuid())
			.createdAt(deleteKafkaDto.getCreatedAt())
			.build();
	}

	public FeedReadKafkaDto toFeedKafkaDto(FeedDto feedDto) {
		return FeedReadKafkaDto.builder()
			.feedCode(feedDto.getFeedCode())
			.uuid(feedDto.getUuid())
			.petCode(feedDto.getPetCode())
			.content(feedDto.getContent())
			.tagList(feedDto.getTagList())
			.state(feedDto.isState())
			.mediaUrlList(feedDto.getMediaUrlList())
			.createdAt(feedDto.getCreatedAt())
			.build();
	}

	public UserKafkaDto toUserKafkaDto(UserProfileDto userProfileDto) {
		return UserKafkaDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.image(userProfileDto.getImage())
			.build();
	}

	public FeedCreateEventDto toFeedCreateEventDto(FeedDto feedDto) {
		return FeedCreateEventDto.builder()
			.feedCode(feedDto.getFeedCode())
			.uuid(feedDto.getUuid())
			.petCode(feedDto.getPetCode())
			.createdAt(feedDto.getCreatedAt())
			.build();
	}

	public FeedDeleteEventDto toFeedDeleteEventDto(FeedDeleteDto feedDeleteDto) {
		return FeedDeleteEventDto.builder()
			.feedCode(feedDeleteDto.getFeedCode())
			.uuid(feedDeleteDto.getUuid())
			.createdAt(feedDeleteDto.getCreatedAt())
			.build();
	}

	public KafkaUserUpdateRequestDto toKafkaUserUpdateRequestDto(UserProfileDto userProfileDto) {
		return KafkaUserUpdateRequestDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.image(userProfileDto.getImage())
			.build();
	}

	public FollowDto toFollowDto(KafkaAlarmFollowResponseDto kafkaAlarmFollowResponseDto) {
		return FollowDto.builder()
			.receiverUuid(kafkaAlarmFollowResponseDto.getReceiverUuid())
			.senderUuid(kafkaAlarmFollowResponseDto.getSenderUuid())
			.createdAt(kafkaAlarmFollowResponseDto.getCreatedAt())
			.build();
	}

	public FeedDeleteKafkaDto toFeedDeleteKafkaDto(FeedDeleteDto feedDeleteDto) {
		return FeedDeleteKafkaDto.builder()
			.feedCode(feedDeleteDto.getFeedCode())
			.uuid(feedDeleteDto.getUuid())
			.createdAt(feedDeleteDto.getCreatedAt())
			.build();
	}

	public UserImageKafkaDto toUserImageKafkaDto(UserProfileDto userProfileDto) {
		return UserImageKafkaDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.image(userProfileDto.getImage())
			.build();
	}

	public UuidKafkaDto toUuidKafkaDto(UserDeleteDto userDeleteDto) {
		return UuidKafkaDto.builder().uuid(userDeleteDto.getUuid()).build();
	}

	public UserNickNameKafkaDto toUserNickNameKafkaDto(UserProfileDto userProfileDto) {
		return UserNickNameKafkaDto.builder()
			.uuid(userProfileDto.getUserUuid())
			.nickname(userProfileDto.getNickname())
			.tag(userProfileDto.getTag())
			.build();
	}

	public FeedCodeResponseDto toFeedCodeResponseDto(FeedDto feedDto) {
		return FeedCodeResponseDto.builder()
			.uuid(feedDto.getUuid())
			.feedCode(feedDto.getFeedCode())
			.build();
	}

	public KafkaFeedCreateRequestDto toKafkaFeedCreateRequestDto(FeedDto feedDto) {
		return KafkaFeedCreateRequestDto.builder()
			.feedCode(feedDto.getFeedCode())
			.tagList(feedDto.getTagList())
			.petCode(feedDto.getPetCode())
			.mediaUrlList(feedDto.getMediaUrlList())
			.build();
	}

	public FeedKafkaRequestDto toFeedKafkaRequestDto(FeedDto feedDto) {
		return FeedKafkaRequestDto.builder()
			.uuid(feedDto.getUuid())
			.feedCode(feedDto.getFeedCode())
			.content(feedDto.getContent())
			.mediaUrlList(feedDto.getMediaUrlList())
			.build();
	}

	public NotificationDto toNotificationDto(NotificationRequestDto notificationRequestDto) {
		return NotificationDto.builder()
			.senderUuid(notificationRequestDto.getSenderUuid())
			.receiverUuidList(notificationRequestDto.getReceiverUuidList())
			.feedCode(notificationRequestDto.getFeedCode())
			.content(notificationRequestDto.getContent())
			.mediaUrl(notificationRequestDto.getMediaUrl())
			.type(notificationRequestDto.getType())
			.build();
	}

	public NotificationFeedRequestDto toNotificationFeedRequestDto(NotificationDto notificationDto) {
		return NotificationFeedRequestDto.builder()
			.senderUuid(notificationDto.getSenderUuid())
			.receiverUuidList(notificationDto.getReceiverUuidList())
			.feedCode(notificationDto.getFeedCode())
			.content(notificationDto.getContent())
			.mediaUrl(notificationDto.getMediaUrl())
			.type(notificationDto.getType())
			.build();
	}

	public NotificationFollowRequestDto toNotificationFollowRequestDto(FollowDto followDto) {
		return NotificationFollowRequestDto.builder()
			.receiverUuid(followDto.getReceiverUuid())
			.senderUuid(followDto.getSenderUuid())
			.build();
	}

	public FollowEventDto toFollowEventDto(FollowDto followDto) {
		return FollowEventDto.builder()
			.senderUuid(followDto.getSenderUuid())
			.receiverUuid(followDto.getReceiverUuid())
			.createdAt(followDto.getCreatedAt())
			.build();
	}

	public FollowProfileDto toFollowProfileDto(FollowKafkaDto followKafkaDto) {
		return FollowProfileDto.builder()
			.senderUuid(followKafkaDto.getSenderUuid())
			.senderNickname(followKafkaDto.getSenderNickname())
			.senderTag(followKafkaDto.getSenderTag())
			.senderImage(followKafkaDto.getSenderImage())
			.receiverUuid(followKafkaDto.getReceiverUuid())
			.receiverNickname(followKafkaDto.getReceiverNickname())
			.receiverTag(followKafkaDto.getReceiverTag())
			.receiverImage(followKafkaDto.getReceiverImage())
			.build();
	}

	public KafkaFollowDto toKafkaFollowDto(FollowProfileDto followProfileDto) {
		return KafkaFollowDto.builder()
			.senderUuid(followProfileDto.getSenderUuid())
			.senderNickname(followProfileDto.getSenderNickname())
			.senderTag(followProfileDto.getSenderTag())
			.senderImage(followProfileDto.getSenderImage())
			.receiverUuid(followProfileDto.getReceiverUuid())
			.receiverNickname(followProfileDto.getReceiverNickname())
			.receiverTag(followProfileDto.getReceiverTag())
			.receiverImage(followProfileDto.getReceiverImage())
			.build();
	}
}
