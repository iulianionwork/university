package university.service.component;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import university.model.Role;
import university.model.UserCredentials;
import university.repository.UserRepository;

@AllArgsConstructor
@Component
public class AdminGenerator implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        if(userRepository.existsByRole(Role.ADMIN)){
            return;
        }

        UserCredentials admin = new UserCredentials(
                "admin@admin.com",
                passwordEncoder.encode("admin"),
                Role.ADMIN);

        userRepository.save(admin);
        System.out.println("Admin with email: " + admin.getEmail() + " and password: admin"  + " has been created");
    }
}
