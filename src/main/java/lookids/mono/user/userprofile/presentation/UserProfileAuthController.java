package lookids.mono.user.userprofile.presentation;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.user.userprofile.application.UserProfileService;

@Slf4j
@RequiredArgsConstructor
@RestController
// @RequestMapping("/user-service/auth/userprofile")
public class UserProfileAuthController {

	private final UserProfileService userProfileService;

	// @Operation(summary = "createUserProfile API", description = "createUserProfile API 입니다.")
	// @PostMapping()
	// public BaseResponse<Void> createUserProfile(@RequestBody UserProfileRequestVo userProfileRequestVo) {
	// 	userProfileService.createUserProfile(UserProfileRequestDto.toDto(userProfileRequestVo));
	// 	return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	// }

}
