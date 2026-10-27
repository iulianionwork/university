package university.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.professor.CreateProfessorRequest;
import university.controller.dto.professor.ProfessorResponse;
import university.controller.dto.student.StudentResponse;
import university.model.entity.Course;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.UserCredentials;
import university.model.repository.CourseRepository;
import university.model.repository.ProfessorRepository;
import university.model.repository.UserRepository;
import university.model.service.CourseService;
import university.model.service.ProfessorService;
import university.model.service.StudentService;
import university.model.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CourseServiceTest {

    @Autowired
    CourseService courseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private CourseRepository courseRepository;

    private Course mate;
    private Course info;


    @BeforeEach
    void setUp() {
        Professor ion = professorRepository.save(new Professor("Ion","Popescu","Info"));
        UserCredentials userIon = new UserCredentials("ion@test.com","parola", Role.PROFESSOR);
        Professor vasile =professorRepository.save(new Professor("Vasile","Samuel","Mate"));
        userIon.setProfessor(ion);
        userRepository.save(userIon);

        mate = courseRepository.save( new Course("Mate", vasile));
        info =courseRepository.save( new Course("Info", ion));

    }

    @Test
    @WithMockUser(username = "ion@test.com", roles = "PROFESSOR")
    void getStudentsOfIonInfoCourses(){
        assertTrue(courseService.getStudentsOfMyCourse(info.getId()).isEmpty());
    }

    @Test
    @WithMockUser(username = "ion@test.com", roles = "PROFESSOR")
    void getStudentsOfVasileMateCourses(){
        assertThrows(AccessDeniedException.class, () -> courseService.getStudentsOfMyCourse(mate.getId()));
    }

    @Test
    @WithMockUser(username = "ion@test.com", roles = "PROFESSOR")
    void getIonCourses(){
        assertEquals(1, courseService.getCoursesOfProfessor().size());
    }


}
