package university.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import university.model.entity.Role;

@Getter @Setter @AllArgsConstructor
public class CreateStudentRequest {
    private String password;
    private Role role;
    private String email;
    private String firstName;
    private String lastName;
}
