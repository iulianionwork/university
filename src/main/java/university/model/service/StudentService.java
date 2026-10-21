package university.model.service;

import university.controller.dto.CreateStudentRequest;
import university.model.entity.Student;

public interface StudentService {
    Student create(CreateStudentRequest request);
}
