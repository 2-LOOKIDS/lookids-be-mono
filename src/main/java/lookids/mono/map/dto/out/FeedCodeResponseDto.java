package lookids.mono.map.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeedCodeResponseDto {

	private String uuid;
	private String feedCode;

	@Builder
	public FeedCodeResponseDto(String feedCode, String uuid) {
		this.feedCode = feedCode;
		this.uuid = uuid;
	}

}
