package university.controller.dto;


public record ProfessorResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String department) {

}
