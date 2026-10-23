package university.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateProfessorRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @NotBlank String department) {

}
