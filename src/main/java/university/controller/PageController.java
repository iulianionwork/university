package university.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import university.model.repository.UserRepository;

@Controller
public class PageController {



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(Authentication authentication){
        return "Log in as " + authentication.getName() + " with role" + authentication.getAuthorities();
    }
}
