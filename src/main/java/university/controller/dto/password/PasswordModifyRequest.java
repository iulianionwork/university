package university.controller.dto.password;

import jakarta.validation.constraints.NotBlank;

public record PasswordModifyRequest(
        @NotBlank String email,
        @NotBlank String newPassword) {
}
