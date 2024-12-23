package lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.model.UpdateOptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.FeedEntity;
import lookids.commentread.comment.adaptor.out.infrastructure.mapper.CommentEntityMapper;
import lookids.commentread.comment.application.port.dto.CommentDeleteSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadUpdateDto;
import lookids.commentread.comment.application.port.dto.ReplyDeleteDto;
import lookids.commentread.comment.application.port.dto.UserProfileUpdateSaveDto;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForRead;
import lookids.commentread.comment.domain.model.FeedCount;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryPort {

	private final MongoTemplate mongoTemplate;
	private final CommentEntityMapper commentEntityMapper;
	private final CommentReadMongoRepository commentReadMongoRepository;

	@Override
	public void createComment(CommentReadSaveDto commentReadSaveDto) {
		mongoTemplate.save(commentEntityMapper.toEntity(commentReadSaveDto));
		updateFeedCommentCount(commentReadSaveDto.getFeedCode(), 1);
	}

	@Override
	public void updateComment(CommentReadUpdateDto commentReadUpdateDto) {
		mongoTemplate.save(commentEntityMapper.toUpdateEntity(commentReadUpdateDto));
		updateFeedCommentCount(commentReadUpdateDto.getFeedCode(), 1);
	}

	@Override
	public Page<CommentForRead> readCommentList(String feedCode, Pageable pageable) {

		return commentEntityMapper.toDomainPage(commentReadMongoRepository.findByFeedCode(feedCode, pageable));
	}

	@Override
	public CommentForRead readComment(String commentCode) {

		// Optional에서 값을 꺼내고, 값이 없을 경우 예외 처리
		CommentReadEntity commentReadEntity;
		try {
			commentReadEntity = commentReadMongoRepository.findByCommentCode(commentCode)
				.orElseThrow(() -> new IllegalArgumentException("Comment not found for code: " + commentCode));
		} catch (Exception e) {
			throw e;  // 예외를 다시 던지거나 적절히 처리
		}

		// commentReadEntity를 사용하여 도메인 객체로 변환
		return commentEntityMapper.toDomain(commentReadEntity);
	}

	@Override
	public void updateUserProfile(UserProfileUpdateSaveDto userProfileUpdateSaveDto) {
		Query commentQuery = new Query(Criteria.where("userUuid").is(userProfileUpdateSaveDto.getUserUuid()));
		mongoTemplate.updateMulti(commentQuery, userProfileUpdateSaveDto.getUpdate(), "comment_entity");

		// 대댓글 갱신
		Query replyQuery = new Query(Criteria.where("replyList.userUuid").is(userProfileUpdateSaveDto.getUserUuid()));
		UpdateOptions options = new UpdateOptions().arrayFilters(
			List.of(new Document("elem.userUuid", userProfileUpdateSaveDto.getUserUuid())));
		mongoTemplate.getCollection("comment_entity")
			.updateMany(replyQuery.getQueryObject(), userProfileUpdateSaveDto.getUpdate().getUpdateObject(), options);
	}

	public void updateReplyCount(String parentCommentCode, int change) {
		mongoTemplate.updateFirst(Query.query(Criteria.where("commentCode").is(parentCommentCode)),
			new Update().inc("replyCount", change), "comment_entity");
	}

	@Override
	public void deleteComment(CommentDeleteSaveDto commentDeleteSaveDto) {
		// 댓글과 대댓글을 포함한 삭제
		mongoTemplate.remove(Query.query(Criteria.where("commentCode").is(commentDeleteSaveDto.getCommentCode())),
			"comment_entity");
		updateFeedCommentCount(commentDeleteSaveDto.getFeedCode(), -commentDeleteSaveDto.getTotalToDelete());
	}

	@Override
	public void deleteReply(ReplyDeleteDto replyDeleteDto) {
		// 댓글에서 대댓글 삭제 및 replyCount 감소
		Query query = Query.query(Criteria.where("replyList.commentCode").is(replyDeleteDto.getCommentCode()));
		Update update = new Update().pull("replyList",
			Query.query(Criteria.where("commentCode").is(replyDeleteDto.getCommentCode()))).inc("replyCount", -1);

		mongoTemplate.updateFirst(query, update, "comment_entity");
	}

	@Override
	public String getFeedCodeByComment(String commentCode) {
		// 부모 댓글의 feedCode 조회
		CommentReadEntity parentComment = mongoTemplate.findOne(
			Query.query(Criteria.where("commentCode").is(commentCode)), CommentReadEntity.class);
		return parentComment != null ? parentComment.getFeedCode() : null;
	}

	@Override
	public void updateFeedCommentCount(String feedCode, int change) {
		// 피드의 댓글 수 갱신
		Query feedQuery = new Query(Criteria.where("feedCode").is(feedCode));
		Update update = new Update().inc("totalCommentCount", change); // 댓글 수 증감
		mongoTemplate.upsert(feedQuery, update, "feed_entity");
	}

	@Override
	public FeedCount readCommentCount(String feedCode) {
		FeedEntity feedEntity = mongoTemplate.findOne(new Query(Criteria.where("feedCode").is(feedCode)),
			FeedEntity.class, "feed_entity");

		if (feedEntity == null) {
			return commentEntityMapper.toNullCountDomain(feedCode);
		}

		return commentEntityMapper.toCountDomain(feedEntity);
	}
}
