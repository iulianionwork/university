package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import university.exception.NotFoundException;
import university.model.entity.Carnet;
import university.model.service.CarnetService;
import university.model.service.CourseService;
import university.model.service.UserService;

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

        try{
            model.addAttribute("carnet", carnetService.myCarnet());
        } catch(NotFoundException e){
            model.addAttribute("carnet", null);
        }

        return "student/home";
    }
}
