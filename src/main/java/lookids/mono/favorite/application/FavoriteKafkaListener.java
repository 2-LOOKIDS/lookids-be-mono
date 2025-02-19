package lookids.mono.favorite.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.favorite.dto.FavoriteFeedDto;
import lookids.mono.favorite.dto.FavoriteResponseDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteKafkaListener {

	private final FavoriteService favoriteService;
	//private final KafkaTemplate<String, FavoriteFeedDto> kafkaTemplate;

	//@KafkaListener(topics = "favorite-request", groupId = "favorite-join-feed", containerFactory = "feedFavoriteEventListenerContainerFactory")
	//public List<String> consumeFeed(FeedKafkaRequestDto requestDto) {
	public List<String> consumeFeed(String uuid) {
		//String uuid = requestDto.getUuid(); // UUID 추출
		List<FavoriteResponseDto> responseDtos = favoriteService.readUserFavoriteList(uuid);
		FavoriteFeedDto favoriteFeedDto = FavoriteFeedDto.builder()
			.uuid(uuid)
			.targetCodeList(responseDtos.stream().map(FavoriteResponseDto::getTargetCode).toList())
			.build();
		//sendMessage("favorite-response", favoriteFeedDto);
		return responseDtos.stream().map(FavoriteResponseDto::getTargetCode).toList();
	}

	// //public void sendMessage(String topic, FavoriteFeedDto favoriteFeedDto) {
	// 	kafkaTemplate.send(topic, favoriteFeedDto);
	// }
}
