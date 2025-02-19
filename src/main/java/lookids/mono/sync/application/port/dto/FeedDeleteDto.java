package lookids.mono.sync.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedDeleteDto {

	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;

	@Builder
	public FeedDeleteDto(String feedCode, String uuid, LocalDateTime createdAt) {
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
	}
}
