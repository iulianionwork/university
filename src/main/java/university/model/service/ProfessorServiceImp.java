package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.CreateStudentRequest;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
import university.model.repository.ProfessorRepository;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

@Service
@AllArgsConstructor
public class ProfessorServiceImp implements ProfessorService {

    private final PasswordEncoder passwordEncoder;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Professor create(CreateProfessorRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }

        Professor professor = new Professor(request.getFirstName(), request.getLastName(),  request.getDepartment());
        professorRepository.save(professor);

        UserCredentials professorUser = new UserCredentials();
        professorUser.setEmail(request.getEmail());
        professorUser.setPassword(passwordEncoder.encode(request.getPassword()));
        professorUser.setRole(Role.PROFESSOR);
        professorUser.setProfessor(professor);
        userRepository.save(professorUser);
        return professor;
    }
}
