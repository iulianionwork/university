package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import university.model.service.CourseService;
import university.model.service.GroupService;
import university.model.service.ProfessorService;
import university.model.service.StudentService;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminPageController {


    private final StudentService studentService;
    private final ProfessorService professorService;
    private final GroupService groupService;
    private final CourseService courseService;


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "Admin Page");
        model.addAttribute("studentCount", studentService.getStudents().size());
        model.addAttribute("professorCount", professorService.getProfessors().size());
        model.addAttribute("groupCount", groupService.getGroups().size());
        model.addAttribute("courseCount", courseService.getAllCourses().size());

        return "admin/home";
    }
}
