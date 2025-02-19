package lookids.mono.batch.feed.application.port.in;

import lookids.mono.batch.feed.application.port.dto.FeedCreateEventDto;
import lookids.mono.batch.feed.application.port.dto.FeedDeleteEventDto;

public interface FeedLogUseCase {

	// void feedCreateLog(List<FeedCreateEventDto> feedCreateEventDto);
	//
	// void feedDeleteLog(List<FeedDeleteEventDto> feedDeleteEventDtoList);

	void feedCreateLog(FeedCreateEventDto feedCreateEventDto);

	void feedDeleteLog(FeedDeleteEventDto feedDeleteEventDtoList);
}
