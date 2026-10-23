package university.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.CreateStudentRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.StudentResponse;
import university.model.service.ProfessorService;
import university.model.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final StudentService studentService;
    private final ProfessorService professorService;



    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponse createStudent(@Valid @RequestBody CreateStudentRequest request) {
         return studentService.create(request);
    }

    @PostMapping("/professors")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorResponse createProfessor(@Valid @RequestBody CreateProfessorRequest request) {
        return professorService.create(request);
    }

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/professors")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfessorResponse> getProfessors() {
        return professorService.getProfessors();
    }
}
