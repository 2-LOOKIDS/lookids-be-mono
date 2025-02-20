package lookids.mono.user.userprofile.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.dto.out.FollowKafkaDto;
import lookids.mono.user.userprofile.dto.out.UserProfileKafkaDto;
import lookids.mono.user.userprofile.infrastructure.UserProfileRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileKafkaListener {
	private final UserProfileRepository userProfileRepository;

	public FollowKafkaDto consumeFollowEvent(String senderUuid, String receiverUuid) {

		UserProfile senderProfile = userProfileRepository.findByUserUuid(senderUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		UserProfile receiverProfile = userProfileRepository.findByUserUuid(receiverUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		return FollowKafkaDto.toDto(senderProfile, receiverProfile);
	}

	public UserProfileKafkaDto consumeCommentEvent(String uuid) {

		log.info("consumeCommentEvent: {}", uuid);

		UserProfile userProfile = userProfileRepository.findByUserUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		return UserProfileKafkaDto.toDto(userProfile);
	}
}
