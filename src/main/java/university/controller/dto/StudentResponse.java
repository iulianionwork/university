package university.controller.dto;


public record StudentResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String groupName) {

}
