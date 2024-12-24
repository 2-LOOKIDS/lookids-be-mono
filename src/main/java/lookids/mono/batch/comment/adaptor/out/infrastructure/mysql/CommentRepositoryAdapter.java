package lookids.mono.batch.comment.adaptor.out.infrastructure.mysql;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.comment.adaptor.out.infrastructure.entity.CommentLogEntity;
import lookids.mono.batch.comment.adaptor.out.infrastructure.mapper.CommentEntityMapper;
import lookids.mono.batch.comment.application.port.dto.CommentLogSaveDto;
import lookids.mono.batch.comment.application.port.out.CommentRepositoryPort;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommentRepositoryAdapter implements CommentRepositoryPort {
	private final CommentLogRepository commentLogRepository;
	private final CommentEntityMapper commentEntityMapper;

	@Override
	public void createLog(List<CommentLogSaveDto> commentLogSaveDtoList) {
		List<CommentLogEntity> commentLogEntityList = commentLogSaveDtoList.stream()
			.map(commentEntityMapper::toCommentLogEntity)
			.toList();
		commentLogRepository.saveAll(commentLogEntityList);
	}

}
