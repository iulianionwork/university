package university.model.service;

import university.controller.dto.CreateProfessorRequest;
import university.model.entity.Professor;

public interface ProfessorService {

    Professor create(CreateProfessorRequest request);
}
