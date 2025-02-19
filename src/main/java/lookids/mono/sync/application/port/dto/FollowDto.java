package lookids.mono.sync.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowDto {
	private String receiverUuid;
	private String senderUuid;
	private LocalDateTime createdAt;

	@Builder
	public FollowDto(String receiverUuid, String senderUuid, LocalDateTime createdAt) {
		this.receiverUuid = receiverUuid;
		this.senderUuid = senderUuid;
		this.createdAt = createdAt;
	}
}
