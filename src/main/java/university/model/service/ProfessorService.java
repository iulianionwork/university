package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.mapper.ProfessorMapper;
import university.model.entity.Professor;
import university.model.entity.Role;
import university.model.entity.UserCredentials;
import university.model.repository.ProfessorRepository;
import university.model.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService{

    private final PasswordEncoder passwordEncoder;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ProfessorResponse create(CreateProfessorRequest request){

        Professor professor = professorMapper.toEntity(request);
        professorRepository.save(professor);

        String email = emailConstruct(professor.getFirstName(), professor.getLastName(), professor.getId());

        UserCredentials professorUser = new UserCredentials();
        professorUser.setEmail(email);
        professorUser.setPassword(passwordEncoder.encode(request.password()));
        professorUser.setRole(Role.PROFESSOR);
        professorUser.setProfessor(professor);
        userRepository.save(professorUser);

        return professorMapper.toDto(professorUser);
    }

    private String emailConstruct(String firstName, String lastName, long id){
        String cleanName = (firstName + "." +lastName).toLowerCase().replaceAll("[^a-z.]","");
        return cleanName + (int)id + "@professor.com";
    }


    public List<ProfessorResponse> getProfessors(){

        return userRepository.findAllProfessors().stream()
                .map(professorMapper::toDto)
                .toList();
    }
}
