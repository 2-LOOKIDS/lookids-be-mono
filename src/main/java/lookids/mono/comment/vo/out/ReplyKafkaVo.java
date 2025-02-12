package lookids.mono.comment.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReplyKafkaVo {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
