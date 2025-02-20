package lookids.mono.feedread.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.feedread.domain.FeedRead;
import lookids.mono.feedread.dto.in.FeedDeleteKafkaDto;
import lookids.mono.feedread.dto.in.FeedReadKafkaDto;
import lookids.mono.feedread.dto.in.PetImageKafkaDto;
import lookids.mono.feedread.dto.in.UserImageKafkaDto;
import lookids.mono.feedread.dto.in.UserKafkaDto;
import lookids.mono.feedread.dto.in.UserNickNameKafkaDto;
import lookids.mono.feedread.dto.in.UuidKafkaDto;
import lookids.mono.feedread.infrastructure.FeedReadRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@ToString
public class FeedKafkaListener {

	//private final KafkaTemplate<String, TargetRequestKafkaDto> recommendKafkaTemplate;

	private final FeedReadRepository feedReadRepository;

	@Transactional
	//@KafkaListener(topics = "userprofile-nickname-update", groupId = "feed-read-group", containerFactory = "userNickNameEventListenerContainerFactory")
	public void nickNameUpdateConsume(UserNickNameKafkaDto userNickNameKafkaDto) {
		List<FeedRead> findUuid = feedReadRepository.findAllByUuid(userNickNameKafkaDto.getUuid());
		if (findUuid.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_FEED);
		}
		List<FeedRead> nickNameUpdate = findUuid.stream()
			.map(userNickNameKafkaDto::toNickNameUpdate)
			.collect(Collectors.toList());
		feedReadRepository.saveAll(nickNameUpdate);
	}

	@Transactional
	//@KafkaListener(topics = "userprofile-image-update", groupId = "feed-read-group", containerFactory = "userImageEventListenerContainerFactory")
	public void imageUpdateConsume(UserImageKafkaDto userImageKafkaDto) {
		List<FeedRead> findUuid = feedReadRepository.findAllByUuid(userImageKafkaDto.getUuid());
		if (findUuid.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_FEED);
		}
		List<FeedRead> imageUpdate = findUuid.stream()
			.map(userImageKafkaDto::toImageUpdate)
			.collect(Collectors.toList());
		feedReadRepository.saveAll(imageUpdate);
	}

	//@KafkaListener(topics = "feed-delete", groupId = "feed-read-group", containerFactory = "deleteEventListenerContainerFactory")
	public void feedDeleteConsume(FeedDeleteKafkaDto feedDeleteKafkaDto) {
		FeedRead feedRead = feedReadRepository.findByFeedCodeAndStateTrue(feedDeleteKafkaDto.getFeedCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
		FeedRead updatedFeedRead = feedDeleteKafkaDto.toUpdatedEntity(feedRead);
		log.info("adskjfhaksljdfh: {}", feedDeleteKafkaDto);
		feedReadRepository.save(updatedFeedRead);
	}

	// @KafkaListener(topics = "recommend-user", groupId = "feed-read-group", containerFactory = "recommendEventListenerContainerFactory")
	// public void recommendTarget(TargetKafkaDto targetKafkaDto) {
	// 	List<FeedRead> findUuidList = feedReadRepository.findByFeedCodeIn(targetKafkaDto.getTargetCode());
	// 	List<String> uuidList = findUuidList.stream().map(FeedRead::getUuid).collect(Collectors.toList());
	// 	TargetRequestKafkaDto targetRequestKafkaDto = TargetRequestKafkaDto.toDto(targetKafkaDto.getAuthorUuid(),
	// 		uuidList);
	// 	recommendKafkaTemplate.send("recommend-user-response", targetRequestKafkaDto);
	// }

	@Transactional
	//@KafkaListener(topics = "petprofile-update", groupId = "feed-read-group", containerFactory = "petProfileEventListenerContainerFactory")
	public void petProfileUpdateConsume(PetImageKafkaDto petImageKafkaDto) {
		List<FeedRead> findPetCode = feedReadRepository.findAllBypetCode(petImageKafkaDto.getPetCode());
		if (findPetCode.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_FEED);
		}
		List<FeedRead> imageUpdateList = findPetCode.stream()
			.map(petImageKafkaDto::toImageUpdate)
			.toList();
		feedReadRepository.saveAll(imageUpdateList);
	}

	//@KafkaListener(topics = "account-delete", groupId = "feed-read-group", containerFactory = "accountDeleteEventListenerContainerFactory")
	public void accountDeleteConsume(UuidKafkaDto uuidKafkaDto) {
		List<FeedRead> findUuid = feedReadRepository.findAllByUuid(uuidKafkaDto.getUuid());
		if (findUuid.isEmpty()) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_FEED);
		}
		List<FeedRead> feedDelete = findUuid.stream().map(uuidKafkaDto::toDelete).collect(Collectors.toList());
		feedReadRepository.saveAll(feedDelete);
	}
	
	public void feedConsume(FeedReadKafkaDto feedReadKafkaDto, UserKafkaDto userKafkaDto) {
		FeedRead feedRead = FeedRead.toEntity(feedReadKafkaDto, userKafkaDto);
		feedReadRepository.save(feedRead);
	}
}
