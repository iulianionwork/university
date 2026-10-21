package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.CreateStudentRequest;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

@Service
@AllArgsConstructor
public class StudentServiceImp implements StudentService {

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Student create(CreateStudentRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        Student student = new Student(request.getFirstName(), request.getLastName());
        studentRepository.save(student);

        UserCredentials studentUser = new UserCredentials();
        studentUser.setEmail(request.getEmail());
        studentUser.setPassword(passwordEncoder.encode(request.getPassword()));
        studentUser.setRole(Role.STUDENT);
        studentUser.setStudent(student);
        userRepository.save(studentUser);
        return student;
    }
}
