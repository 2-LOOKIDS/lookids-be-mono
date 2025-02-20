package lookids.mono.user.petprofile.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.user.petprofile.domain.PetProfile;
import lookids.mono.user.petprofile.infrastructure.PetProfileRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetProfileKafkaListener {
	private final PetProfileRepository petProfileRepository;

	public String findPetImage(String petCode) {

		log.info("consumeFeedKafkaVo: {}", petCode);

		PetProfile petProfile = petProfileRepository.findByPetCode(petCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		return petProfile.getImage();
	}
}
