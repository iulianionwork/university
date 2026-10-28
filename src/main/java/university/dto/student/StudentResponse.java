package university.dto.student;


public record StudentResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String groupName) {

}
