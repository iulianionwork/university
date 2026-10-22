package university.controller.dto;

public record UpdateProfessorRequest(
        String firstName,
        String lastName,
        String department) {
}
