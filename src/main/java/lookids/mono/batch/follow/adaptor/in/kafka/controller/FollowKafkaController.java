package lookids.mono.batch.follow.adaptor.in.kafka.controller;

//
// @Slf4j
// @RequiredArgsConstructor
// @Component
public class FollowKafkaController {

	// private final FollowLogUseCase followLogUseCase;
	// private final FollowDtoMapper followDtoMapper;
	//
	// @KafkaListener(topics = "${follow.create}", groupId = "${group-id.batch}", containerFactory = "followBatchListenerContainerFactory")
	// public void consumeFollowEvents(List<FollowEvent> followEventList, Acknowledgment acknowledgment) {
	// 	try {
	// 		log.info("follow create log processing start");
	// 		followLogUseCase.followCreateLog(
	// 			followEventList.stream().map(followDtoMapper::toFollowEventDto).collect(Collectors.toList())); // 배치 처리
	// 		log.info("follow create log processing end");
	// 		// Acknowledgment가 있으면 오프셋 커밋
	// 		if (acknowledgment != null) {
	// 			acknowledgment.acknowledge();
	// 		}
	// 	} catch (Exception e) {
	// 		log.error("Message processing failed: {} ", e);
	// 		// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
	// 	}
	// }
	//
	// @KafkaListener(topics = "${follow.delete}", groupId = "${group-id.batch}", containerFactory = "followBatchListenerContainerFactory")
	// public void consumeFollowDeleteEvents(List<FollowEvent> followEventList, Acknowledgment acknowledgment) {
	// 	try {
	// 		log.info("follow delete log processing start");
	// 		followLogUseCase.followDeleteLog(
	// 			followEventList.stream().map(followDtoMapper::toFollowEventDto).collect(Collectors.toList())); // 배치 처리
	// 		log.info("follow delete log processing end");
	// 		// Acknowledgment가 있으면 오프셋 커밋
	// 		if (acknowledgment != null) {
	// 			acknowledgment.acknowledge();
	// 		}
	// 	} catch (Exception e) {
	// 		log.error("Message processing failed: {} ", e);
	// 		// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
	// 	}
	// }
}