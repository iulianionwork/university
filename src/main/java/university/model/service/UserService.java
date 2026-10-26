package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import university.exception.NotFoundException;
import university.model.entity.Professor;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserCredentials user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));

            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();

    }

    private UserCredentials get(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
    }

    public Professor getProfessor(){
        Professor professor = get().getProfessor();
        if(professor == null){
            throw new AccessDeniedException("User is not logged as a professor");
        }

        return professor;
    }

    public Student getStudent(){
        Student student = get().getStudent();
        if(student == null){
            throw new AccessDeniedException("User is not logged as a student");
        }
        return student;
    }


}
