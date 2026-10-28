package university.dto.group;

import jakarta.validation.constraints.NotBlank;

public record GroupRequest(
        @NotBlank String groupName) {
}
