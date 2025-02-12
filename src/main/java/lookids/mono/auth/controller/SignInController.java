package lookids.mono.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.dto.in.OAuthSignUpRequestDto;
import lookids.mono.auth.dto.in.RefreshTokenRequestDto;
import lookids.mono.auth.dto.in.SignInRequestDto;
import lookids.mono.auth.dto.out.RefreshTokenResponseDto;
import lookids.mono.auth.dto.out.SignInResponseDto;
import lookids.mono.auth.service.SignInService;
import lookids.mono.auth.vo.in.OAuthSignUpRequestVo;
import lookids.mono.auth.vo.in.RefreshTokenRequestVo;
import lookids.mono.auth.vo.in.SignInRequestVo;
import lookids.mono.auth.vo.out.RefreshTokenResponseVo;
import lookids.mono.auth.vo.out.SignInResponseVo;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth")
public class SignInController {
	private final SignInService signInService;

	@Operation(summary = "login API", description = "login API 입니다.", tags = {"Auth"})
	@PostMapping("/sign-in")
	public BaseResponse<SignInResponseVo> signIn(@RequestBody SignInRequestVo signInRequestVo) {
		SignInResponseDto signInResponseDto = signInService.signIn(SignInRequestDto.toDto(signInRequestVo));
		log.info("signInResponseDto: {}", signInResponseDto);
		return new BaseResponse<>(signInResponseDto.toVo());

	}

	@Operation(summary = "OAuth sign API", description = "OAuth 회원가입/로그인 API 입니다. (미가입 시 회원가입, 가입 시 로그인 서비스 호출)", tags = {
		"OAuth"})
	@PostMapping("/social-sign")
	public BaseResponse<SignInResponseVo> oAuthSignUp(@RequestBody OAuthSignUpRequestVo oAuthSignUpRequestVo) {
		SignInResponseDto signInResponseDto = signInService.oAuthSign(
			OAuthSignUpRequestDto.toDto(oAuthSignUpRequestVo));
		return new BaseResponse<>(signInResponseDto.toVo());
	}

	@Operation(summary = "refresh-accesstoken API", description = "refresh-accesstoken API 입니다. Body에 RefreshToken을 입력하세요.", tags = {
		"Auth"})
	@PostMapping("/refresh-accesstoken")
	public BaseResponse<RefreshTokenResponseVo> refreshAccessToken(@RequestHeader("uuid") String uuid,
		@RequestBody RefreshTokenRequestVo refreshTokenRequestVo) {
		RefreshTokenRequestDto refreshTokenRequestDto = RefreshTokenRequestDto.toDto(
			refreshTokenRequestVo.getRefreshToken(), uuid);
		RefreshTokenResponseDto refreshTokenResponseDto = signInService.refreshAccessToken(refreshTokenRequestDto);
		return new BaseResponse<>(refreshTokenResponseDto.toVo());
	}
}
