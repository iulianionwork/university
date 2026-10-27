package university.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import university.controller.dto.carnet.CarnetResponse;
import university.controller.dto.course.CourseRequest;
import university.controller.dto.course.CourseResponse;
import university.controller.dto.course.CourseUpdate;
import university.controller.dto.group.GroupRequest;
import university.controller.dto.group.GroupResponse;
import university.controller.dto.professor.CreateProfessorRequest;
import university.controller.dto.professor.ProfessorResponse;
import university.controller.dto.professor.UpdateProfessorRequest;
import university.controller.dto.student.CreateStudentRequest;
import university.controller.dto.student.StudentResponse;
import university.controller.dto.student.UpdateStudentRequest;
import university.model.service.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final StudentService studentService;
    private final ProfessorService professorService;
    private final FakeDataService fakeDataService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final CarnetService carnetService;


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
    public GroupResponse createGroup(@Valid @RequestBody GroupRequest request) {
        return groupService.createGroup(request);
    }

    @GetMapping("/groups")
    @ResponseStatus(HttpStatus.OK)
    public List<GroupResponse> getGroups() {
        return groupService.getGroups();
    }

    @GetMapping("/groups/{groupId}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsGroups(@PathVariable Long groupId) {
        return groupService.getStudentsFromGroup(groupId);
    }

    @PutMapping("/students/{studentId}/group/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        studentService.assignStudentToGroup(groupId, studentId);
    }

    @GetMapping("/students/without-group")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsWithoutGroup() {
        return studentService.getStudentsWithoutGroup();
    }

    @GetMapping("/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseResponse> getCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@Valid @RequestBody CourseRequest request) {
        return courseService.createCourse(request);
    }

    @GetMapping("/courses/{courseId}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsCourses(@PathVariable Long courseId) {
        return courseService.getStudentsByCourseId(courseId);
    }

    @PutMapping("/courses/{courseId}/professor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCourse(@PathVariable Long courseId, @Valid @RequestBody CourseUpdate request) {
        courseService.updateCourseProfessor(request, courseId);
    }

    @PostMapping("/courses/{courseId}/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.enrollStudentToCourse(courseId, studentId);
    }

    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unenrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.unenrollStudentToCourse(courseId, studentId);
    }

    @DeleteMapping("/courses/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
    }

    @PostMapping("/students/{studentId}/carnet")
    @ResponseStatus(HttpStatus.CREATED)
    public CarnetResponse createCarnet(@PathVariable Long studentId) {
        return carnetService.issueCarnet(studentId);
    }

    @GetMapping("/students/without-carnet")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentResponse> getStudentsWithoutCarnet() {
        return studentService.getStudentsWithoutCarnet();
    }

    @PutMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void studentUpdate(@PathVariable Long studentId, @RequestBody UpdateStudentRequest request) {
        studentService.updateStudent(studentId, request);

    }

    @PutMapping("/professors/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void professorUpdate(@PathVariable Long professorId, @RequestBody UpdateProfessorRequest request) {
        professorService.updateProfessor(professorId, request);
    }

    @DeleteMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @DeleteMapping("/professors/{professorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfessor(@PathVariable Long professorId) {
        professorService.deleteProfessor(professorId);
    }

    @DeleteMapping("/groups/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.deleteGroup(groupId);
    }



}
