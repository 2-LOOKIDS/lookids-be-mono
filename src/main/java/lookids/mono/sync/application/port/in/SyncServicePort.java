package lookids.mono.sync.application.port.in;

import lookids.mono.sync.application.port.dto.CommentDto;
import lookids.mono.sync.application.port.dto.ReplyDto;
import lookids.mono.sync.application.port.dto.UserDeleteDto;

public interface SyncServicePort {
	void userDelete(UserDeleteDto userDeleteDto);

	void createComment(CommentDto commentDto);

	void createReply(ReplyDto replyDto);

	void deleteComment(CommentDto commentCreateDto);

	void deleteReply(ReplyDto replyCreateDto);
}
