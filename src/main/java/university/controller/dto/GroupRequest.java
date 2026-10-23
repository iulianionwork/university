package university.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record GroupRequest(
        @NotBlank String groupName) {
}
