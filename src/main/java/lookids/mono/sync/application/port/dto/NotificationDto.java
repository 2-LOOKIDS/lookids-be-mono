package lookids.mono.sync.application.port.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String feedCode;
	private String content;
	private String mediaUrl;
	private String type;

	@Builder
	public NotificationDto(String senderUuid, List<String> receiverUuidList, String feedCode,
		String content, String mediaUrl, String type) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.feedCode = feedCode;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.type = type;
	}
}
