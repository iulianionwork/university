package university.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.professor.CreateProfessorRequest;
import university.dto.professor.ProfessorResponse;
import university.dto.professor.UpdateProfessorRequest;
import university.dto.student.StudentResponse;
import university.mapper.ProfessorMapper;
import university.exception.NotFoundException;
import university.model.Professor;
import university.model.Role;
import university.model.UserCredentials;
import university.repository.ProfessorRepository;
import university.repository.UserRepository;
import university.service.component.EmailConfig;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService{

    private final PasswordEncoder passwordEncoder;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;
    private final EmailConfig  emailConfig;


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ProfessorResponse create(CreateProfessorRequest request){

        Professor professor = professorMapper.toEntity(request);
        professorRepository.save(professor);

        String email = emailConfig.emailConstruct(
                professor.getFirstName(),
                professor.getLastName(),
                professor.getId(),
                "@professor.com");

        UserCredentials professorUser = new UserCredentials(
                email,
                passwordEncoder.encode(request.password()),
                Role.PROFESSOR);

        professorUser.setProfessor(professor);
        userRepository.save(professorUser);

        return professorMapper.toDto(professorUser);
    }



    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfessorResponse> getProfessors(){

        return userRepository.findAllProfessors().stream()
                .map(professorMapper::toDto)
                .toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void updateProfessor(Long professorId, UpdateProfessorRequest request){
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new NotFoundException("Professor not found: " + professorId));

        professorMapper.updateEntity(request, professor);
        professorRepository.save(professor);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProfessor(Long professorId){
        UserCredentials professorUser = userRepository.findByProfessorId(professorId)
                .orElseThrow(() -> new NotFoundException("Professor not found: " + professorId));
        Professor professor = professorUser.getProfessor();


        if(!professor.getCourses().isEmpty()){
            throw new IllegalStateException("Professor still has courses!");
        }

        userRepository.delete(professorUser);
        professorRepository.delete(professor);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ProfessorResponse getProfessorById(Long professorId){
        UserCredentials professorUser = userRepository.findByProfessorId(professorId)
                .orElseThrow(() -> new NotFoundException("Professor not found: " + professorId));
        return professorMapper.toDto(professorUser);
    }
}
