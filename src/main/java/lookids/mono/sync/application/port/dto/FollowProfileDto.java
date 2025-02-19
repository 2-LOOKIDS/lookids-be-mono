package lookids.mono.sync.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowProfileDto {
	private String senderUuid;
	private String senderNickname;
	private String senderTag;
	private String senderImage;

	private String receiverUuid;
	private String receiverNickname;
	private String receiverTag;
	private String receiverImage;

	@Builder
	public FollowProfileDto(String senderUuid, String senderNickname, String senderTag, String senderImage,
		String receiverUuid, String receiverNickname, String receiverTag, String receiverImage) {
		this.senderUuid = senderUuid;
		this.senderNickname = senderNickname;
		this.senderTag = senderTag;
		this.senderImage = senderImage;
		this.receiverUuid = receiverUuid;
		this.receiverNickname = receiverNickname;
		this.receiverTag = receiverTag;
		this.receiverImage = receiverImage;
	}

}
