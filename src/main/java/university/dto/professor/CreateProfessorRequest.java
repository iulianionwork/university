package university.dto.professor;

import jakarta.validation.constraints.NotBlank;

public record CreateProfessorRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password,
        @NotBlank String department) {

}
