package university.controller.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.dto.course.CourseResponse;
import university.dto.password.PasswordChangeRequest;
import university.dto.professor.ProfessorResponse;
import university.dto.student.StudentResponse;
import university.model.service.CourseService;
import university.model.service.PasswordService;
import university.model.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/professor")
public class ProfessorRestController {

    private final CourseService courseService;
    private final UserService userService;
    private final PasswordService passwordService;


    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ProfessorResponse getCurrentProfessor() {
        return userService.getCurrentProfessor();
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getCourses() {
        return courseService.getCoursesOfProfessor();
    }

    @GetMapping("/courses/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsOfCourseById(@PathVariable Long courseId){
        return courseService.getStudentsOfMyCourse(courseId);
    }

    @PutMapping("/me/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@Valid @RequestBody PasswordChangeRequest request){
        passwordService.userRequestPasswordModification(request);
    }

}
