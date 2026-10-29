package university.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import university.dto.student.CreateStudentRequest;
import university.dto.student.StudentResponse;
import university.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    @WithMockUser(roles = "ADMIN")
    void createStudentAndVerifyTheGeneratedEmail() {
        StudentResponse studentResponse = studentService.create(new CreateStudentRequest(
                "Ion",
                "Popescu",
                "student"
        ));

        assertEquals("ion.popescu"+ studentResponse.id() + "@student.com", studentResponse.email());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void deleteAnInexistentStudent() {
        assertThrows(NotFoundException.class, () -> studentService.deleteStudent(874L));
    }


    @Test
    @WithMockUser(roles = "STUDENT")
    void createAsAStudent() {
        assertThrows(AuthorizationDeniedException.class, () -> studentService.create(new CreateStudentRequest(
                "Vasile",
                "Popescu",
                "student"
        )));
    }
}
