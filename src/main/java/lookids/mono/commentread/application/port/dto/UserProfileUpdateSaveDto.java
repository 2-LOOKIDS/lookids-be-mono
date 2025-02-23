package lookids.mono.commentread.application.port.dto;

import org.springframework.data.mongodb.core.query.Update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileUpdateSaveDto {
	private String userUuid;
	private String nickName;
	private String tag;
	private String image;
	private Update update;

	@Builder
	public UserProfileUpdateSaveDto(String userUuid, String nickName, String tag, String image, Update update) {
		this.userUuid = userUuid;
		this.nickName = nickName;
		this.tag = tag;
		this.image = image;
		this.update = update;
	}
}
