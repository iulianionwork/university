package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import university.service.*;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminPageController {


    private final StudentService studentService;
    private final ProfessorService professorService;
    private final GroupService groupService;
    private final CourseService courseService;
    private final CarnetService carnetService;


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Admin Page");
        model.addAttribute("studentCount", studentService.getStudents().size());
        model.addAttribute("professorCount", professorService.getProfessors().size());
        model.addAttribute("groupCount", groupService.getGroups().size());
        model.addAttribute("courseCount", courseService.getAllCourses().size());

        return "admin/home";
    }

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getStudents());

        return "admin/student/studentsList";
    }

    @GetMapping("/students/{studentId}")
    public String studentProfile(@PathVariable Long studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("courses", courseService.getCoursesOfStudentById(studentId));
        model.addAttribute("carnet", carnetService.findCarnetByStudentId(studentId).orElse(null));
        return "admin/student/studentProfile";
    }

    @GetMapping("/professors")
    public String professors(Model model) {
        model.addAttribute("professors", professorService.getProfessors());

        return "admin/professor/professorsList";
    }

    @GetMapping("/professors/{professorId}")
    public String professorProfile(@PathVariable Long professorId, Model model) {
        model.addAttribute("professor", professorService.getProfessorById(professorId));
        model.addAttribute("courses", courseService.getCoursesOfProfessorById(professorId));
        return "admin/professor/professorProfile";
    }

    @GetMapping("/groups")
    public String groups(Model model) {
        model.addAttribute("groups", groupService.getGroups());
        return "admin/group/groupsList";
    }

    @GetMapping("/groups/{groupId}")
    public String groupProfile(@PathVariable Long groupId, Model model) {
        model.addAttribute("students", groupService.getStudentsFromGroup(groupId));
        model.addAttribute("groupName", groupService.getGroupById(groupId).groupName());

        return "admin/group/groupProfile";
    }

    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "admin/course/courseList";
    }

    @GetMapping("/courses/{courseId}")
    public String courseProfile(@PathVariable Long courseId, Model model) {
        model.addAttribute("course", courseService.getCourseById(courseId));
        model.addAttribute("students", courseService.getStudentsByCourseId(courseId));
        return "admin/course/courseProfile";
    }

    @PostMapping("/courses/{courseId}/students/{studentId}/unenroll")
    public String unenrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
        courseService.unenrollStudentFromCourse(courseId, studentId);
        return "redirect:/admin/courses/{courseId}";
    }

    @PostMapping("/students/{studentId}/carnet")
    public String createCarnet(@PathVariable Long studentId) {
        carnetService.issueCarnet(studentId);
        return "redirect:/admin/students/{studentId}";
    }

}
