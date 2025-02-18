package lookids.mono.sync.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavoriteDto {

	private String uuid;
	private String targetCode;
	private Boolean favoriteState;
	private String favoriteType;
	private String receiverUuid;

	@Builder
	public FavoriteDto(String uuid, String targetCode, Boolean favoriteState, String favoriteType,
		String receiverUuid) {
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.favoriteState = favoriteState;
		this.favoriteType = favoriteType;
		this.receiverUuid = receiverUuid;
	}
}
