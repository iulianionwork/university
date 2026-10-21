package university.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CreateStudentRequest {
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
