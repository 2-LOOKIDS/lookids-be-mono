package lookids.mono.chatting.vo.out;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ParticipantListVo {
	private String roomId;
	private String roomName;
	private List<String> participants;
}
