package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import university.model.service.CourseService;
import university.model.service.UserService;

@Controller
@RequestMapping("/professor")
@AllArgsConstructor
public class ProfessorPageController {

    private final UserService userService;
    private final CourseService courseService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("me", userService.getCurrentProfessor());
        model.addAttribute("courses", courseService.getCoursesOfProfessor());
        return "professor/home";
    }

    @GetMapping("/courses/{courseId}/students")
    public String courses(@PathVariable Long courseId, Model model) {
        model.addAttribute("courseName", courseService.getCourseById(courseId).courseName());
       model.addAttribute("students", courseService.getStudentsOfMyCourse(courseId));
       return "professor/students";
    }
}
