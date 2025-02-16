package lookids.mono.sync.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;

	@Builder
	public CommentDto(String commentCode, String feedCode, String feedUuid, String content,
		LocalDateTime createdAt, String uuid) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.feedUuid = feedUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.uuid = uuid;
	}
}
