package university.controller.page;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import university.dto.password.PasswordChangeRequest;
import university.dto.password.PasswordModifyRequest;
import university.service.PasswordService;
import university.service.ProfessorService;
import university.service.StudentService;

@Controller
@AllArgsConstructor
public class PasswordPageController {

    private final PasswordService passwordService;
    private final StudentService studentService;
    private final ProfessorService professorService;


    @GetMapping("/password")
    public String passwordPage(){
        return "changePasswordForm";
    }

    @PostMapping("/password")
    public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest(oldPassword, newPassword);
        passwordService.userRequestPasswordModification(passwordChangeRequest);
        return "redirect:/logout";
    }

    @GetMapping("admin/students/{studentId}/password")
    public String passwordStudentPage(@PathVariable Long studentId, Model model){
        model.addAttribute("email", studentService.getStudentById(studentId).email());
        return "admin/changePasswordForm";
    }

    @PostMapping("admin/password")
    public String adminChangePassword(@RequestParam String email, @RequestParam String newPassword) {
        PasswordModifyRequest passwordModifyRequest = new PasswordModifyRequest(email, newPassword);
        passwordService.adminRequestPasswordChange(passwordModifyRequest);
        return "redirect:admin/home";
    }

    @GetMapping("admin/professors/{professorId}/password")
    public String passwordProfessorPage(@PathVariable Long professorId, Model model){
        model.addAttribute("email", professorService.getProfessorById(professorId).email());
        return "admin/changePasswordForm";
    }
}
