package university.dto.professor;

public record UpdateProfessorRequest(
        String firstName,
        String lastName,
        String department) {
}
