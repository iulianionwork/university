package university.controller.mapper;

import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.UpdateProfessorRequest;
import university.model.entity.Professor;
import university.model.entity.UserCredentials;

import java.util.Optional;

public class ProfessorMapper implements ToDtoMapper<UserCredentials, ProfessorResponse>, ToEntityMapper<CreateProfessorRequest, Professor>, UpdateMapper<UpdateProfessorRequest, Professor> {


    @Override
    public ProfessorResponse toDto(UserCredentials user) {
        Professor professor = user.getProfessor();
        return new ProfessorResponse(
                professor.getId(),
                user.getEmail(),
                professor.getFirstName(),
                professor.getLastName(),
                professor.getDepartment());
    }

    @Override
    public Professor toEntity(CreateProfessorRequest dto) {
        return new Professor(dto.firstName(),  dto.lastName(), dto.department());
    }

    @Override
    public void updateEntity(UpdateProfessorRequest dto, Professor professor) {
        Optional.ofNullable(dto.firstName())
                .ifPresent(professor::setFirstName);

        Optional.ofNullable(dto.lastName())
                .ifPresent(professor::setLastName);

        Optional.ofNullable(dto.department())
                .ifPresent(professor::setDepartment);
    }
}
