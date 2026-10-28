package university.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CourseRequest(
        @NotBlank String courseName,
        @NotNull Long professorId) {
}
