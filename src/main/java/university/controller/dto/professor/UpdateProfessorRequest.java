package university.controller.dto.professor;

public record UpdateProfessorRequest(
        String firstName,
        String lastName,
        String department) {
}
