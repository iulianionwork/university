package university.model.service;

import university.controller.dto.CreateProfessorRequest;
import university.controller.dto.ProfessorResponse;

public interface ProfessorService {

    ProfessorResponse create(CreateProfessorRequest request);
}
