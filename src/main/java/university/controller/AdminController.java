package university.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.controller.dto.*;
import university.model.repository.StudentRepository;
import university.model.service.FakeDataService;
import university.model.service.GroupService;
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
    private final GroupService groupService;


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

    @PostMapping("/fake/students/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateStudents(@PathVariable int count) {
        fakeDataService.generateStudents(count);
        return "Generated " + count + " students";
    }

     @PostMapping("/fake/professors/mate/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateMateProfessor(@PathVariable int count) {
         fakeDataService.generateMateProfessor(count);
        return "Generated " + count + " mate professors";
    }

    @PostMapping("/fake/professors/info/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateInfoProfessor(@PathVariable int count) {
        fakeDataService.generateInfoProfessor(count);
        return "Generated " + count + " info professors";
    }

    @PostMapping("/fake/professors/italy/{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public String generateItalyProfessor(@PathVariable int count) {
        fakeDataService.generateItalianProfessor(count);
        return "Generated " + count + " italy professors";
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public GroupResponse createGroup(@RequestBody GroupRequest request) {
        return groupService.createGroup(request);
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupResponse> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/groups/{id}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudents(@PathVariable Long id) {
        return groupService.getStudentsFromGroup(id);
    }

    @PutMapping("/students/{studentId}/group/{groupId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudentToGroup(@PathVariable Long studentId, @PathVariable Long groupId) {
        studentService.assignStudentToGroup(studentId, groupId);
    }

    @GetMapping("/students/without-group")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsWithoutGroup() {
        return studentService.getStudentsWhitoutGroup();
    }


}
