package university.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.CreateStudentRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.StudentResponse;
import university.model.service.ProfessorService;
import university.model.service.StudentService;
import university.model.service.UserService;

@RestController
@AllArgsConstructor
public class AdminController {

    private UserService userService;
    private StudentService studentService;
    private ProfessorService professorService;



    @PostMapping("/admin/students")
    public StudentResponse create(@RequestBody CreateStudentRequest request) {
         return studentService.create(request);
    }

    @PostMapping("/admin/professors")
    public ProfessorResponse create(@RequestBody CreateProfessorRequest request) {
        return professorService.create(request);
    }
}
