package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.dto.password.PasswordChangeRequest;
import university.exception.NotFoundException;
import university.service.*;

@Controller
@RequestMapping("/student")
@AllArgsConstructor
public class StudentPageController {

    private final UserService userService;
    private final CourseService courseService;
    private final CarnetService carnetService;
    private final StudentService studentService;
    private final PasswordService passwordService;

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

    @PostMapping("/password")
    public String changePassword( @RequestParam String oldPassword, @RequestParam String newPassword) {
        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest(oldPassword, newPassword);
        passwordService.userRequestPasswordModification(passwordChangeRequest);
        return "redirect:/logout";
    }

    @GetMapping("/password")
    public String goToPasswordForm() {
        return "student/changePassword";
    }
}
