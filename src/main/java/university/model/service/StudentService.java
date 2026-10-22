package university.model.service;

import university.controller.dto.CreateStudentRequest;
import university.controller.dto.StudentResponse;

public interface StudentService {
    StudentResponse create(CreateStudentRequest request);
}
