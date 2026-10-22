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

@Service
@AllArgsConstructor
public class ProfessorServiceImp implements ProfessorService {

    private final PasswordEncoder passwordEncoder;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final ProfessorMapper professorMapper;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ProfessorResponse create(CreateProfessorRequest request){

        if(userRepository.existsByEmail(request.email())){
            throw new IllegalArgumentException("Email already exists");
        }

        Professor professor = professorMapper.toEntity(request);
        professorRepository.save(professor);

        UserCredentials professorUser = new UserCredentials();
        professorUser.setEmail(request.email());
        professorUser.setPassword(passwordEncoder.encode(request.password()));
        professorUser.setRole(Role.PROFESSOR);
        professorUser.setProfessor(professor);
        userRepository.save(professorUser);

        return professorMapper.toDto(professorUser);
    }
}
