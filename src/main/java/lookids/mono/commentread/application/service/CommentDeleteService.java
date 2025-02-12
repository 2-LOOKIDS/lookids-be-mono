package lookids.mono.commentread.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lookids.mono.commentread.application.mapper.CommentReadDtoMapper;
import lookids.mono.commentread.application.port.dto.CommentDeleteDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;
import lookids.mono.commentread.application.port.in.CommentDeleteUseCase;
import lookids.mono.commentread.application.port.out.CommentRepositoryPort;
import lookids.mono.commentread.domain.model.CommentForRead;

@RequiredArgsConstructor
@Service
public class CommentDeleteService implements CommentDeleteUseCase {

	private final CommentRepositoryPort commentRepositoryPort;

	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public void deleteComment(CommentDeleteDto commentDeleteDto) {
		// 댓글 조회
		CommentForRead comment = commentRepositoryPort.readComment(commentDeleteDto.getCommentCode());

		if (comment != null) {
			int totalToDelete = 1 + (comment.getReplyForReadList() != null ? comment.getReplyForReadList().size() : 0);

			// 댓글 삭제
			commentRepositoryPort.deleteComment(
				commentReadDtoMapper.toCommentDeleteSaveDto(commentDeleteDto, totalToDelete));
		}
	}

	@Override
	@Transactional
	public void deleteReply(ReplyDeleteDto replyDeleteDto) {
		// 대댓글 삭제
		commentRepositoryPort.deleteReply(replyDeleteDto);

		// 대댓글을 포함한 댓글의 feedCode를 사용하여 피드의 댓글 수 갱신
		String feedCode = commentRepositoryPort.getFeedCodeByComment(replyDeleteDto.getParentCommentCode());
		if (feedCode != null) {
			commentRepositoryPort.updateFeedCommentCount(feedCode, -1);
		}
	}
}
