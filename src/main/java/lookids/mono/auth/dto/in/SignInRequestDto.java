package lookids.mono.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.auth.vo.in.SignInRequestVo;

@Getter
@NoArgsConstructor
public class SignInRequestDto {

	private String loginId;
	private String password;

	@Builder
	public SignInRequestDto(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}

	public static SignInRequestDto toDto(SignInRequestVo signInRequestVo) {
		return SignInRequestDto.builder()
			.loginId(signInRequestVo.getLoginId())
			.password(signInRequestVo.getPassword())
			.build();
	}

}