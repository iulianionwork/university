package university.controller.mapper;

import org.springframework.stereotype.Component;
import university.controller.dto.CourseRequest;
import university.controller.dto.CourseResponse;
import university.controller.dto.CourseUpdate;
import university.model.entity.Course;
import university.model.entity.Professor;

@Component
public class CourseMapper implements ToDtoMapper<Course, CourseResponse> , ToEntityMapper<CourseRequest, Course> , UpdateMapper<CourseUpdate, Course> {
    @Override
    public CourseResponse toDto(Course entity) {
        Professor professor = entity.getProfessor();
        String professorName = professor.getFirstName() + " " + professor.getLastName();
        return new CourseResponse(
                entity.getId(),
                entity.getCourseName(),
                professorName);
    }

    @Override
    public Course toEntity(CourseRequest dto) {
        return new Course(dto.courseName(), dto.professor());
    }

    @Override
    public void updateEntity(CourseUpdate dto, Course entity) {
        entity.setProfessor(dto.professor());

    }
}
