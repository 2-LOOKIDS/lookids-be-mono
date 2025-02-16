package lookids.mono.sync.application.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.auth.dto.in.AccountDeleteKafkaRequestDto;
import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;
import lookids.mono.chatting.dto.in.UserKafkaRequestDto;
import lookids.mono.comment.vo.out.CommentKafkaVo;
import lookids.mono.comment.vo.out.ReplyKafkaVo;
import lookids.mono.commentread.application.port.dto.CommentCreateEventDto;
import lookids.mono.commentread.application.port.dto.CommentDeleteDto;
import lookids.mono.commentread.application.port.dto.ReplyCreateEventDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;
import lookids.mono.sync.application.port.dto.CommentDto;
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
}
