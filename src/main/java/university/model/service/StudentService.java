package university.model.service;

import university.controller.dto.CreateStudentRequest;
import university.controller.dto.StudentResponse;
import university.model.entity.Student;

import java.util.List;

public interface StudentService {
    StudentResponse create(CreateStudentRequest request);

    List<StudentResponse> getStudents();
}
