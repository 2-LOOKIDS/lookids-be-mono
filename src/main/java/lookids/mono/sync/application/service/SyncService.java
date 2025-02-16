package lookids.mono.sync.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.batch.comment.application.port.in.CommentLogUseCase;
import lookids.mono.chatting.application.UserKafkaListener;
import lookids.mono.commentread.application.port.in.CommentDeleteUseCase;
import lookids.mono.commentread.application.port.in.CommentReadCreateUseCase;
import lookids.mono.commentread.application.port.in.UserProfileUpdateUseCase;
import lookids.mono.sync.application.mapper.SyncDtoMapper;
import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;
import lookids.mono.sync.application.port.dto.UserProfileDto;
import lookids.mono.sync.application.port.in.SyncServicePort;
import lookids.mono.user.userprofile.application.UserProfileService;

@RequiredArgsConstructor
@Service
public class SyncService implements SyncServicePort {

	private final SyncDtoMapper syncDtoMapper;
	private final UserKafkaListener userKafkaListener;

	private final CommentLogUseCase commentLogUseCase;

	private final CommentReadCreateUseCase commentReadCreateUseCase;
	private final UserProfileUpdateUseCase userProfileUpdateUseCase;
	private final CommentDeleteUseCase commentDeleteUseCase;

	private final UserProfileService userProfileService;

	@Override
	public void userDelete(UserDeleteDto userDeleteDto) {
		userKafkaListener.userDelete(syncDtoMapper.toUserKafkaRequestDto(userDeleteDto));
	}

	@Override
	public void createComment(CommentDto commentDto) {
		UserProfileDto userProfileDto = syncDtoMapper.toUserProfileDto(
			userProfileService.consumeCommentEvent(commentDto.getUuid()));
		commentReadCreateUseCase.createCommentRead(
			syncDtoMapper.toCommentCreateEventDto(userProfileDto, commentDto));
		commentLogUseCase.commentCreateLog(syncDtoMapper.toCommentCreateBatchDto(commentDto));
	}

	@Override
	public void createReply(ReplyDto replyDto) {
		UserProfileDto userProfileDto = syncDtoMapper.toUserProfileDto(
			userProfileService.consumeCommentEvent(replyDto.getUuid()));
		commentReadCreateUseCase.createReplyRead(syncDtoMapper.toReplyCreateEventDto(userProfileDto, replyDto));
		commentLogUseCase.replyCreateLog(syncDtoMapper.toReplyCreateBatchDto(replyDto));
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
}
