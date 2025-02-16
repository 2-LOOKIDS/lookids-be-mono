package lookids.mono.batch.comment.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.comment.application.mapper.CommentDtoMapper;
import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;
import lookids.mono.batch.comment.application.port.in.CommentLogUseCase;
import lookids.mono.batch.comment.application.port.out.CommentRepositoryPort;
import lookids.mono.batch.comment.domain.model.CommentLog;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLogService implements CommentLogUseCase {

	private final CommentDtoMapper commentDtoMapper;
	private final CommentRepositoryPort commentRepositoryPort;

	// @Override
	// public void commentCreateLog(List<CommentCreateEventDto> commentCreateEventDtoList) {
	// 	// List<CommentLog> commentLogList = commentCreateEventDtoList.stream()
	// 	// 	.map(commentCreateEventDto -> CommentLog.builder()
	// 	// 		.commentCode(commentCreateEventDto.getCommentCode())
	// 	// 		.feedCode(commentCreateEventDto.getFeedCode())
	// 	// 		.uuid(commentCreateEventDto.getUuid())
	// 	// 		.createdAt(commentCreateEventDto.getCreatedAt())
	// 	// 		.logType("create")
	// 	// 		.commentType("comment")
	// 	// 		.build())
	// 	// 	.toList();
	// 	// commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	// }

	// @Override
	// public void commentDeleteLog(List<CommentCreateEventDto> commentCreateEventDtoList) {
	// 	List<CommentLog> commentLogList = commentCreateEventDtoList.stream()
	// 		.map(commentCreateEventDto -> CommentLog.builder()
	// 			.commentCode(commentCreateEventDto.getCommentCode())
	// 			.feedCode(commentCreateEventDto.getFeedCode())
	// 			.uuid(commentCreateEventDto.getUuid())
	// 			.createdAt(commentCreateEventDto.getCreatedAt())
	// 			.logType("delete")
	// 			.commentType("comment")
	// 			.build())
	// 		.toList();
	// 	commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	// }
	//
	//
	// @Override
	// public void replyCreateLog(List<ReplyCreateEventDto> replyCreateEventDtoList) {
	// 	List<CommentLog> commentLogList = replyCreateEventDtoList.stream()
	// 		.map(replyCreateEventDto -> CommentLog.builder()
	// 			.commentCode(replyCreateEventDto.getCommentCode())
	// 			.feedCode(replyCreateEventDto.getFeedCode())
	// 			.uuid(replyCreateEventDto.getUuid())
	// 			.createdAt(replyCreateEventDto.getCreatedAt())
	// 			.parentCommentCode(replyCreateEventDto.getParentCommentCode())
	// 			.logType("create")
	// 			.commentType("reply")
	// 			.build())
	// 		.toList();
	// 	commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	// }
	//
	// @Override
	// public void replyDeleteLog(List<ReplyCreateEventDto> replyCreateEventDtoList) {
	// 	List<CommentLog> commentLogList = replyCreateEventDtoList.stream()
	// 		.map(replyCreateEventDto -> CommentLog.builder()
	// 			.commentCode(replyCreateEventDto.getCommentCode())
	// 			.feedCode(replyCreateEventDto.getFeedCode())
	// 			.uuid(replyCreateEventDto.getUuid())
	// 			.createdAt(replyCreateEventDto.getCreatedAt())
	// 			.parentCommentCode(replyCreateEventDto.getParentCommentCode())
	// 			.logType("delete")
	// 			.commentType("reply")
	// 			.build())
	// 		.toList();
	// 	commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	// }

	@Override
	public void commentCreateLog(CommentCreateBatchDto commentCreateBatchDto) {

		CommentLog commentLog = CommentLog.builder()
			.commentCode(commentCreateBatchDto.getCommentCode())
			.feedCode(commentCreateBatchDto.getFeedCode())
			.uuid(commentCreateBatchDto.getUuid())
			.createdAt(commentCreateBatchDto.getCreatedAt())
			.logType("create")
			.commentType("comment")
			.build();
		commentRepositoryPort.createLog(commentDtoMapper.toCommentLogSaveDto(commentLog));
	}

	@Override
	public void commentDeleteLog(CommentCreateBatchDto commentCreateBatchDto) {
		CommentLog commentLog = CommentLog.builder()
			.commentCode(commentCreateBatchDto.getCommentCode())
			.feedCode(commentCreateBatchDto.getFeedCode())
			.uuid(commentCreateBatchDto.getUuid())
			.createdAt(commentCreateBatchDto.getCreatedAt())
			.logType("delete")
			.commentType("comment")
			.build();
		commentRepositoryPort.createLog(commentDtoMapper.toCommentLogSaveDto(commentLog));
	}

	@Override
	public void replyCreateLog(ReplyCreateBatchDto replyCreateBatchDto) {
		CommentLog commentLog = CommentLog.builder()
			.commentCode(replyCreateBatchDto.getCommentCode())
			.feedCode(replyCreateBatchDto.getFeedCode())
			.uuid(replyCreateBatchDto.getUuid())
			.createdAt(replyCreateBatchDto.getCreatedAt())
			.parentCommentCode(replyCreateBatchDto.getParentCommentCode())
			.logType("create")
			.commentType("reply")
			.build();
		commentRepositoryPort.createLog(commentDtoMapper.toCommentLogSaveDto(commentLog));
	}

	@Override
	public void replyDeleteLog(ReplyCreateBatchDto replyCreateBatchDto) {
		CommentLog commentLog = CommentLog.builder()
			.commentCode(replyCreateBatchDto.getCommentCode())
			.feedCode(replyCreateBatchDto.getFeedCode())
			.uuid(replyCreateBatchDto.getUuid())
			.createdAt(replyCreateBatchDto.getCreatedAt())
			.parentCommentCode(replyCreateBatchDto.getParentCommentCode())
			.logType("delete")
			.commentType("reply")
			.build();
		commentRepositoryPort.createLog(commentDtoMapper.toCommentLogSaveDto(commentLog));
	}
}