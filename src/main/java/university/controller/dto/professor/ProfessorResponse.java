package university.controller.dto.professor;


public record ProfessorResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String department) {

}
