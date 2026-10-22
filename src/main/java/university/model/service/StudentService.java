package university.model.service;

import university.controller.dto.CreateStudentRequest;
import university.controller.dto.StudentResponse;
import university.model.entity.Student;

public interface StudentService {
    StudentResponse create(CreateStudentRequest request);
}
