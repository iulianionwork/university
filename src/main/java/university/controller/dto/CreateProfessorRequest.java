package university.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CreateProfessorRequest {
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String department;
}
