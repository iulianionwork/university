package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import university.service.*;

@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentPageController {

    private final UserService userService;
    private final CourseService courseService;
    private final CarnetService carnetService;


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("me", userService.getCurrentStudent());
        model.addAttribute("courses", courseService.getCoursesOfStudent());
        model.addAttribute("carnet", carnetService.myCarnet().orElse(null));
        return "student/home";
    }

}
