package lookids.mono.batch.comment.application.port.out;

import lookids.mono.batch.comment.application.port.dto.CommentLogSaveDto;

public interface CommentRepositoryPort {
	//void createLog(List<CommentLogSaveDto> commentLogSaveDtoList);
	void createLog(CommentLogSaveDto commentLogSaveDto);
}
