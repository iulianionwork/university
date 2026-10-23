package university.model.service;

import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;
import university.controller.dto.StudentResponse;

import java.util.List;

public interface ProfessorService {

    ProfessorResponse create(CreateProfessorRequest request);
    List<ProfessorResponse> getProfessors();
}
