package university.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.CreateStudentRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.StudentResponse;
import university.model.service.FakeDataService;
import university.model.service.ProfessorService;
import university.model.service.StudentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final FakeDataService fakeDataService;


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

    /*

    Hibernate: select uc1_0.id,uc1_0.email,uc1_0.password,uc1_0.professor_id,uc1_0.role,uc1_0.student_id from users uc1_0 where uc1_0.email=?
Hibernate: select uc1_0.id,uc1_0.email,uc1_0.password,uc1_0.professor_id,uc1_0.role,uc1_0.student_id from users uc1_0 join student s1_0 on s1_0.id=uc1_0.student_id
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?
Hibernate: select s1_0.id,s1_0.first_name,s1_0.university_group,s1_0.last_name from student s1_0 where s1_0.id=?

     */

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

    @PostMapping("/fake/students/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateStudents(@PathVariable int count) {
        fakeDataService.generateStudents(count);
        return "Generated " + count + " students";
    }

    /* @PostMapping("/fake/professors/mate/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateMateProfessor(@PathVariable int count) {
        fakeData.generateMateProfessor(count);
        return "Generated " + count + " mate professors";
    }

    @PostMapping("/fake/professors/info/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateInfoProfessor(@PathVariable int count) {
        fakeData.generateInfoProfessor(count);
        return "Generated " + count + " info professors";
    }

    @PostMapping("/fake/professors/italy/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateItalyProfessor(@PathVariable int count) {
        fakeData.generateItalianProfessor(count);
        return "Generated " + count + " italy professors";
    } */


}
