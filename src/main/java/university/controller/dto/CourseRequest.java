package university.controller.dto;

import jakarta.validation.constraints.NotBlank;
import university.model.entity.Professor;

public record CourseRequest(
        @NotBlank String courseName,
        @NotBlank Professor professor) {
}
