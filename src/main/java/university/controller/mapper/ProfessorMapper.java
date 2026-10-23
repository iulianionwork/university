package university.controller.mapper;

import org.springframework.stereotype.Component;
import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.UpdateProfessorRequest;
import university.model.entity.Professor;
import university.model.entity.UserCredentials;

import java.util.Optional;

@Component
public class ProfessorMapper implements ToDtoMapper<UserCredentials, ProfessorResponse>, ToEntityMapper<CreateProfessorRequest, Professor>, UpdateMapper<UpdateProfessorRequest, Professor> {


    @Override
    public ProfessorResponse toDto(UserCredentials dto) {
        Professor professor = dto.getProfessor();
        return new ProfessorResponse(
                professor.getId(),
                dto.getEmail(),
                professor.getFirstName(),
                professor.getLastName(),
                professor.getDepartment());
    }

    @Override
    public Professor toEntity(CreateProfessorRequest dto) {
        return new Professor(dto.firstName(),  dto.lastName(), dto.department());
    }

    @Override
    public void updateEntity(UpdateProfessorRequest dto, Professor entity) {
        Optional.ofNullable(dto.firstName())
                .ifPresent(entity::setFirstName);

        Optional.ofNullable(dto.lastName())
                .ifPresent(entity::setLastName);

        Optional.ofNullable(dto.department())
                .ifPresent(entity::setDepartment);
    }
}
