package lookids.mono.sync.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDeleteDto {
    private String uuid;

    @Builder
    public UserDeleteDto(String uuid) {
        this.uuid = uuid;
    }
}
