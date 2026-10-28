package university.controller.page;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home")
    public String home(Authentication authentication){
        for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
            switch (grantedAuthority.getAuthority()){
                case "ROLE_ADMIN": return "redirect:/admin/home";
                case "ROLE_STUDENT": return "redirect:/student/home";
                case "ROLE_PROFESSOR": return "redirect:/professor/home";
            }
        }
        return "redirect:/login";
    }
}
