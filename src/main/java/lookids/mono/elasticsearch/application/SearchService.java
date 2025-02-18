package lookids.mono.elasticsearch.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.elasticsearch.dto.in.KafkaUserCreateDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserImageUpdateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserNicknameUpdateRequestDto;
import lookids.mono.elasticsearch.dto.out.SearchFeedResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchPetResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchUserResponseDto;

public interface SearchService {

	Page<SearchUserResponseDto> searchUser(String searchUser, Pageable pageable);

	Page<SearchFeedResponseDto> searchFeedByTag(String searchFeed, Pageable pageable);

	Page<SearchFeedResponseDto> searchFeedByPetCode(String searchFeed, Pageable pageable);

	Page<SearchPetResponseDto> searchPet(String searchFeed, Pageable pageable);

	void consumeUserCreate(KafkaUserCreateDto kafkaUserCreateDto);

	void consumeUserNicknameUpdate(KafkaUserNicknameUpdateRequestDto kafkaUserNicknameUpdateRequestDto);

	void consumeUserImageUpdate(KafkaUserImageUpdateRequestDto kafkaUserImageUpdateRequestDto);

	void consumeUserDelete(KafkaUserDeleteRequestDto kafkaUserDeleteRequestDto);

}
