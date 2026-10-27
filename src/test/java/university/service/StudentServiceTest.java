package university.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.student.CreateStudentRequest;
import university.controller.dto.student.StudentResponse;
import university.exception.NotFoundException;
import university.model.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    StudentResponse studentResponse;

    @BeforeEach
    public void setUp(){
        studentResponse = studentService.create(new CreateStudentRequest(
                "Ion",
                "Popescu",
                "student"
        ));
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void createStudentAndVerifyTheGeneratedEmail() {


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
                "Ion",
                "Popescu",
                "student"
        )));
    }
}
