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
import university.controller.dto.professor.ProfessorResponse;
import university.controller.dto.student.StudentResponse;
import university.controller.mapper.ProfessorMapper;
import university.controller.mapper.StudentMapper;
import university.exception.NotFoundException;
import university.model.entity.Professor;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final ProfessorMapper professorMapper;


    @Override
    public UserDetails loadUserByUsername(String email) {

        UserCredentials user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found"));

            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();

    }

    public UserCredentials get(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
    }

    public StudentResponse getCurrentStudent(){
        Student student = get().getStudent();
        if(student == null){
            throw new AccessDeniedException("User is not logged as a student");
        }

        return studentMapper.toDto(get());
    }

    public ProfessorResponse getCurrentProfessor(){
        Professor professor = get().getProfessor();
        if(professor == null){
            throw new AccessDeniedException("User is not logged as a professor");
        }

        return professorMapper.toDto(get());
    }




}
