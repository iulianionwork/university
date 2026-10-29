package university.controller.rest;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.dto.carnet.CarnetResponse;
import university.dto.course.CourseResponse;
import university.dto.password.PasswordChangeRequest;
import university.dto.student.StudentResponse;
import university.service.CarnetService;
import university.service.CourseService;
import university.service.PasswordService;
import university.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/student")
public class StudentRestController {

    private final CourseService courseService;
    private final UserService userService;
    private final CarnetService carnetService;
    private final PasswordService passwordService;

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

    @GetMapping("/carnet")
    @ResponseStatus(HttpStatus.OK)
    public CarnetResponse getCarnet() {
        return carnetService.myCarnet();
    }

    @PutMapping("/me/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassword(@Valid @RequestBody PasswordChangeRequest request){
        passwordService.userRequestPasswordModification(request);
    }
}
