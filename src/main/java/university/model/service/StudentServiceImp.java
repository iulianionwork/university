package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.CreateStudentRequest;
import university.controller.dto.StudentResponse;
import university.controller.mapper.StudentMapper;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImp implements StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse create(CreateStudentRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = studentMapper.toEntity(request);
        studentRepository.save(student);

        UserCredentials studentUser = new UserCredentials();
        studentUser.setEmail(request.email());
        studentUser.setPassword(passwordEncoder.encode(request.password()));
        studentUser.setRole(Role.STUDENT);
        studentUser.setStudent(student);
        userRepository.save(studentUser);

        return studentMapper.toDto(studentUser);
    }

    @Override
    public List<StudentResponse> getStudents(){
        List<UserCredentials> usersStudents = userRepository.findByRole(Role.STUDENT);
        List<StudentResponse> studentResponses = new ArrayList<>();

        for(UserCredentials user : usersStudents){
                studentResponses.add(studentMapper.toDto(user));
        }
        return studentResponses;
    }
}
