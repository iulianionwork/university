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
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public Student getStudent(){
        UserCredentials user = get();
        if(user.getStudent() == null){
            throw new AccessDeniedException("User is not logged as a student");
        }

        return user.getStudent();
    }

    @Transactional
    public Professor getProfessor(){
        UserCredentials user = get();
        if(user.getProfessor() == null){
            throw new AccessDeniedException("User is not logged as a professor");
        }

        return user.getProfessor();
    }

    @Transactional
    public StudentResponse getCurrentStudent(){
        return studentMapper.toDto(get());
    }

    @Transactional
    public ProfessorResponse getCurrentProfessor(){
        return professorMapper.toDto(get());
    }




}
