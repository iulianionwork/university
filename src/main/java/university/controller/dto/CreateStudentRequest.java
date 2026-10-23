package university.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateStudentRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String password) {

}
