package university.model.service;

import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;
import university.model.entity.Professor;

public interface ProfessorService {

    ProfessorResponse create(CreateProfessorRequest request);
}
