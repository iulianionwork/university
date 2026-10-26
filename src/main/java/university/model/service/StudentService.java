package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.student.CreateStudentRequest;
import university.controller.dto.student.StudentResponse;
import university.controller.mapper.StudentMapper;
import university.exception.NotFoundException;
import university.model.entity.Group;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.GroupRepository;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService{

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse create(CreateStudentRequest request){

        Student student = studentMapper.toEntity(request);
        studentRepository.save(student);

        String email = emailConstruct(student.getFirstName(), student.getLastName(), student.getId());

        UserCredentials studentUser = new UserCredentials();
        studentUser.setEmail(email);
        studentUser.setPassword(passwordEncoder.encode(request.password()));
        studentUser.setRole(Role.STUDENT);
        studentUser.setStudent(student);
        userRepository.save(studentUser);

        return studentMapper.toDto(studentUser);
    }

    private String emailConstruct(String firstName, String lastName, long id){
        String cleanName = (firstName + "." +lastName).toLowerCase().replaceAll("[^a-z.]","");
        return cleanName + id + "@student.com";
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> getStudents(){
        return userRepository.findAllStudents().stream()
                .map(studentMapper::toDto)
                .toList();
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void assignStudentToGroup(Long groupId, Long studentId){

        Student student = studentRepository.findById(studentId)
                .orElseThrow(()-> new NotFoundException("Student not found: " + studentId));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(()-> new NotFoundException("Group not found: " + groupId));

        student.setGroup(group);

    }
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> getStudentsWithoutGroup(){
        return userRepository.getStudentsWithoutGroup().stream()
                .map(studentMapper::toDto)
                .toList();
    }

}
