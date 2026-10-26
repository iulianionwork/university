package university.controller.dto.group;

import jakarta.validation.constraints.NotBlank;

public record GroupRequest(
        @NotBlank String groupName) {
}
