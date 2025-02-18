package lookids.mono.sync.application.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.auth.dto.in.AccountDeleteKafkaRequestDto;
import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteUpdateEventDto;
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
import lookids.mono.elasticsearch.dto.in.KafkaUserCreateDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserImageUpdateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserNicknameUpdateRequestDto;
import lookids.mono.favorite.dto.FavoriteRequestDto;
import lookids.mono.notification.dto.in.NotificationChattingRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentReplyRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentRequestDto;
import lookids.mono.notification.dto.in.NotificationFavoriteRequestDto;
import lookids.mono.sync.application.port.dto.ChatDto;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.FavoriteDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;
import lookids.mono.sync.application.port.dto.UserProfileDto;
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

}
