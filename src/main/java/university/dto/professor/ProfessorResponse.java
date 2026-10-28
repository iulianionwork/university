package university.dto.professor;


public record ProfessorResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String department) {

}
