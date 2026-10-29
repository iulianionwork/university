package university.service;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.professor.ProfessorResponse;
import university.dto.student.StudentResponse;
import university.mapper.ProfessorMapper;
import university.mapper.StudentMapper;
import university.exception.NotFoundException;
import university.model.Professor;
import university.model.Student;
import university.model.UserCredentials;
import university.repository.UserRepository;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final ProfessorMapper professorMapper;


    @Override
    @NullMarked
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
