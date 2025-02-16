package lookids.mono.batch.comment.application.port.in;

import lookids.mono.batch.comment.application.port.dto.CommentCreateBatchDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateBatchDto;

public interface CommentLogUseCase {
	// // void commentCreateLog(List<CommentCreateEventDto> commentCreateEventDtoList);
	//
	// void commentDeleteLog(List<CommentCreateEventDto> commentCreateEventDtoList);
	//
	// void replyCreateLog(List<ReplyCreateEventDto> replyCreateEventDtoList);
	//
	// void replyDeleteLog(List<ReplyCreateEventDto> replyCreateEventDtoList);

	void commentCreateLog(CommentCreateBatchDto commentCreateBatchDto);

	void commentDeleteLog(CommentCreateBatchDto commentCreateBatchDto);

	void replyCreateLog(ReplyCreateBatchDto replyCreateBatchDto);

	void replyDeleteLog(ReplyCreateBatchDto replyCreateBatchDto);

}
