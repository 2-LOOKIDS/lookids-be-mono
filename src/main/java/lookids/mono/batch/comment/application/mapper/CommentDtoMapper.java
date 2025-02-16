package lookids.mono.batch.comment.application.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.batch.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.mono.batch.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.CommentLogSaveDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;
import lookids.mono.batch.comment.domain.model.CommentLog;

@Component
public class CommentDtoMapper {

	public CommentCreateBatchDto toCommentCreateEventDto(CommentEvent commentEvent) {
		return CommentCreateBatchDto.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.uuid(commentEvent.getUuid())
			.createdAt(commentEvent.getCreatedAt())
			.build();
	}

	public CommentLogSaveDto toCommentLogSaveDto(CommentLog commentLog) {
		return CommentLogSaveDto.builder()
			.commentCode(commentLog.getCommentCode())
			.feedCode(commentLog.getFeedCode())
			.uuid(commentLog.getUuid())
			.createdAt(commentLog.getCreatedAt())
			.parentCommentCode(commentLog.getParentCommentCode())
			.logType(commentLog.getLogType())
			.commentType(commentLog.getCommentType())
			.build();
	}

	public ReplyCreateBatchDto toReplyCreateEventDto(ReplyEvent replyEvent) {
		return ReplyCreateBatchDto.builder()
			.commentCode(replyEvent.getCommentCode())
			.feedCode(replyEvent.getFeedCode())
			.uuid(replyEvent.getUuid())
			.createdAt(replyEvent.getCreatedAt())
			.parentCommentCode(replyEvent.getParentCommentCode())
			.build();
	}
}
