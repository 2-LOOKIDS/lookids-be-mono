package lookids.mono.sync.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PetProfileDto {
	private String petCode;
	private String petName;
	private String petType;
	private String petImage;
	private String userNickname;
	private String userTag;

	@Builder
	public PetProfileDto(String petCode, String petName, String petType, String petImage,
		String userNickname, String userTag) {
		this.petCode = petCode;
		this.petName = petName;
		this.petType = petType;
		this.petImage = petImage;
		this.userNickname = userNickname;
		this.userTag = userTag;
	}
}
