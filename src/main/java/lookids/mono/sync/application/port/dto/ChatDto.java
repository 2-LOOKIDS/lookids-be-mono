package lookids.mono.sync.application.port.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatDto {
	private String senderUuid;
	private String roomId;
	private List<String> receiverUuidList;
	private String content;
	private String mediaUrl;

	@Builder
	public ChatDto(String senderUuid, String roomId, List<String> receiverUuidList, String content,
		String mediaUrl) {
		this.senderUuid = senderUuid;
		this.roomId = roomId;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.mediaUrl = mediaUrl;
	}
}
