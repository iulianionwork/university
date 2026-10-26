package university.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import university.controller.dto.course.CourseResponse;
import university.controller.dto.student.StudentResponse;
import university.model.service.CourseService;
import university.model.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentRestController {

    private final CourseService courseService;
    private final UserService userService;

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public StudentResponse getCurrentStudent() {
            return userService.getCurrentStudent();
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getCourses() {
        return courseService.getCoursesOfStudent();
    }
}
