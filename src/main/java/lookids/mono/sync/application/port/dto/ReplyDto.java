package lookids.mono.sync.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyDto {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public ReplyDto(String commentCode, String feedCode, String feedUuid, String content,
		LocalDateTime createdAt, String uuid, String parentCommentCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.feedUuid = feedUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.uuid = uuid;
		this.parentCommentCode = parentCommentCode;
	}
}
