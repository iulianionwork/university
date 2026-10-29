package university.mapper;


import org.springframework.stereotype.Component;
import university.dto.student.CreateStudentRequest;
import university.dto.student.StudentResponse;
import university.dto.student.UpdateStudentRequest;
import university.model.Student;
import university.model.UserCredentials;

import java.util.Optional;

@Component
public class StudentMapper implements ToDtoMapper<UserCredentials, StudentResponse>, ToEntityMapper<CreateStudentRequest, Student>, UpdateMapper<UpdateStudentRequest, Student> {
    @Override
    public StudentResponse toDto(UserCredentials dto) {
        Student student = dto.getStudent();
        return new StudentResponse(
                student.getId(),
                dto.getEmail(),
                student.getFirstName(),
                student.getLastName(),
                student.getGroup() == null ? null : student.getGroup().getGroupName());
    }


    @Override
    public Student toEntity(CreateStudentRequest dto) {

        return new Student(dto.firstName(),  dto.lastName());
    }

    @Override
    public void updateEntity(UpdateStudentRequest dto, Student entity) {
        Optional.ofNullable(dto.firstName())
                .ifPresent(entity::setFirstName);

        Optional.ofNullable(dto.lastName())
                .ifPresent(entity::setLastName);
    }
}
