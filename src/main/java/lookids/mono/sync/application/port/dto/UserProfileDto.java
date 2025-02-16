package lookids.mono.sync.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileDto {
	private String userUuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public UserProfileDto(String userUuid, String nickname, String tag, String image) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}
}
