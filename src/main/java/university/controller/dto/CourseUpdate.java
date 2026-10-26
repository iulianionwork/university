package university.controller.dto;

import jakarta.validation.constraints.NotBlank;
import university.model.entity.Professor;

public record CourseUpdate(
        @NotBlank Professor professor) {
}
