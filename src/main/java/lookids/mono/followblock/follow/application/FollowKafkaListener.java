package lookids.mono.followblock.follow.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.followblock.follow.domain.Follow;
import lookids.mono.followblock.follow.domain.FollowInfo;
import lookids.mono.followblock.follow.dto.in.KafkaFollowDto;
import lookids.mono.followblock.follow.dto.in.KafkaUserUpdateRequestDto;
import lookids.mono.followblock.follow.dto.out.KafkaFollowResponseDto;
import lookids.mono.followblock.follow.infrastructure.FollowInfoRepository;
import lookids.mono.followblock.follow.infrastructure.FollowRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowKafkaListener {
	private final FollowInfoRepository followInfoRepository;
	private final FollowRepository followRepository;

	public void consumeUserNicknameUpdate(KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {

		List<FollowInfo> senderList = followInfoRepository.findBySenderUuid(kafkaUserUpdateRequestDto.getUuid());
		List<FollowInfo> receiverList = followInfoRepository.findByReceiverUuid(kafkaUserUpdateRequestDto.getUuid());

		for (FollowInfo sender : senderList) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(sender.getId())
				.senderUuid(sender.getSenderUuid())
				.senderNickname(kafkaUserUpdateRequestDto.getNickname())
				.senderTag(kafkaUserUpdateRequestDto.getTag())
				.senderImage(sender.getSenderImage())
				.receiverUuid(sender.getReceiverUuid())
				.receiverNickname(sender.getReceiverNickname())
				.receiverTag(sender.getReceiverTag())
				.receiverImage(sender.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);
		}

		for (FollowInfo receiver : receiverList) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(receiver.getId())
				.senderUuid(receiver.getSenderUuid())
				.senderNickname(receiver.getSenderNickname())
				.senderTag(receiver.getSenderTag())
				.senderImage(receiver.getSenderImage())
				.receiverUuid(receiver.getReceiverUuid())
				.receiverNickname(kafkaUserUpdateRequestDto.getNickname())
				.receiverTag(kafkaUserUpdateRequestDto.getTag())
				.receiverImage(receiver.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);
		}

	}

	public void consumeUserImageUpdate(KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {

		List<FollowInfo> sender = followInfoRepository.findBySenderUuid(kafkaUserUpdateRequestDto.getUuid());
		List<FollowInfo> receiver = followInfoRepository.findByReceiverUuid(kafkaUserUpdateRequestDto.getUuid());

		for (FollowInfo send : sender) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(send.getId())
				.senderUuid(send.getSenderUuid())
				.senderNickname(send.getSenderNickname())
				.senderTag(send.getSenderTag())
				.senderImage(kafkaUserUpdateRequestDto.getImage())
				.receiverUuid(send.getReceiverUuid())
				.receiverNickname(send.getReceiverNickname())
				.receiverTag(send.getReceiverTag())
				.receiverImage(send.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);

		}

		for (FollowInfo receive : receiver) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(receive.getId())
				.senderUuid(receive.getSenderUuid())
				.senderNickname(receive.getSenderNickname())
				.senderTag(receive.getSenderTag())
				.senderImage(receive.getSenderImage())
				.receiverUuid(receive.getReceiverUuid())
				.receiverNickname(receive.getReceiverNickname())
				.receiverTag(receive.getReceiverTag())
				.receiverImage(kafkaUserUpdateRequestDto.getImage())
				.build();
			followInfoRepository.save(followInfo);

		}
	}

	public void consumeFollowInfo(KafkaFollowDto kafkaFollowDto) {

		followInfoRepository.save(kafkaFollowDto.toEntity());

	}

	public List<String> consumeForFollowUuid(String uuid) {
		List<Follow> followList = followRepository.findByFollowingUuid(uuid);
		return KafkaFollowResponseDto.toDto(uuid, followList).getFollowUuid();
	}
}
