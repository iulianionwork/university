package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import university.dto.password.PasswordChangeRequest;
import university.service.CourseService;
import university.service.PasswordService;
import university.service.UserService;

@Controller
@RequestMapping("/professor")
@AllArgsConstructor
public class ProfessorPageController {

    private final UserService userService;
    private final CourseService courseService;
    private final PasswordService passwordService;

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

    @PostMapping("/password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest(oldPassword, newPassword);
        passwordService.userRequestPasswordModification(passwordChangeRequest);
        return "redirect:/logout";
    }

    @GetMapping("/password")
    public String goToPasswordForm() {
        return "professor/changePassword";
    }
}
