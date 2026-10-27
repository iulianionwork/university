package university.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.controller.dto.carnet.CarnetResponse;
import university.controller.dto.course.CourseResponse;
import university.controller.dto.password.PasswordChangeRequest;
import university.controller.dto.student.StudentResponse;
import university.model.service.CarnetService;
import university.model.service.CourseService;
import university.model.service.PasswordService;
import university.model.service.UserService;

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

    @PutMapping("/me/passowrd")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePassowrd(@RequestBody PasswordChangeRequest request){
        passwordService.userRequestPasswordModification(request);
    }
}
