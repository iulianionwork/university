package university.controller.mapper;


import university.controller.dto.CreateStudentRequest;
import university.controller.dto.StudentResponse;
import university.controller.dto.UpdateStudentRequest;
import university.model.entity.Student;
import university.model.entity.UserCredentials;

import java.util.Optional;

public class StudentMapper implements ToDtoMapper<UserCredentials, StudentResponse>, ToEntityMapper<CreateStudentRequest, Student>, UpdateMapper<UpdateStudentRequest, Student> {
    @Override
    public StudentResponse toDto(UserCredentials user) {
        Student student = user.getStudent();
        return new StudentResponse(
                student.getId(),
                user.getEmail(),
                student.getFirstName(),
                student.getLastName(),
                student.getGroup().getGroupName());
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
