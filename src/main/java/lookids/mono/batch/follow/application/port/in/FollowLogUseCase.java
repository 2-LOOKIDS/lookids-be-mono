package lookids.mono.batch.follow.application.port.in;

import lookids.mono.batch.follow.application.port.dto.FollowEventDto;

public interface FollowLogUseCase {
	//
	// void followCreateLog(List<FollowEventDto> followEventDtoList);
	//
	// void followDeleteLog(List<FollowEventDto> followEventDtoList);
	void followCreateLog(FollowEventDto followEventDto);

	void followDeleteLog(FollowEventDto followEventDto);
}
