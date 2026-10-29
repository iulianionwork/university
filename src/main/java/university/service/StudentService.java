package university.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.student.CreateStudentRequest;
import university.dto.student.StudentResponse;
import university.dto.student.UpdateStudentRequest;
import university.mapper.StudentMapper;
import university.exception.NotFoundException;
import university.model.Group;
import university.model.Role;
import university.model.Student;
import university.model.UserCredentials;
import university.repository.GroupRepository;
import university.repository.StudentRepository;
import university.repository.UserRepository;
import university.service.component.EmailGenerator;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService{

    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final StudentMapper studentMapper;
    private final GroupRepository groupRepository;
    private final EmailGenerator emailGenerator;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse create(CreateStudentRequest request){

        Student student = studentMapper.toEntity(request);
        studentRepository.save(student);

        String email = emailGenerator.generate(
                student.getFirstName(),
                student.getLastName(),
                student.getId(),
                "@student.com");

        UserCredentials studentUser = new UserCredentials(
                email,
                passwordEncoder.encode(request.password()),
                Role.STUDENT);

        studentUser.setStudent(student);
        userRepository.save(studentUser);

        return studentMapper.toDto(studentUser);
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

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> getStudentsWithoutCarnet(){
        return userRepository.getStudentsWithoutCarnet().stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void updateStudent(Long studentId, UpdateStudentRequest request){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        studentMapper.updateEntity(request, student);
        studentRepository.save(student);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(Long studentId){
        UserCredentials studentUser = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
        Student student = studentUser.getStudent();
        student.getCourses().clear();
        userRepository.delete(studentUser);
        studentRepository.delete(student);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public StudentResponse getStudentById(Long studentId){
        UserCredentials studentUser = userRepository.findByStudentId(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
        return studentMapper.toDto(studentUser);
    }

}
