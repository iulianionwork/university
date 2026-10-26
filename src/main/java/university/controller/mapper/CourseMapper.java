package university.controller.mapper;

import org.springframework.stereotype.Component;
import university.controller.dto.course.CourseResponse;
import university.model.entity.Course;
import university.model.entity.Professor;

@Component
public class CourseMapper implements ToDtoMapper<Course, CourseResponse>  {
    @Override
    public CourseResponse toDto(Course entity) {
        Professor professor = entity.getProfessor();
        String professorName = professor.getFirstName() + " " + professor.getLastName();
        return new CourseResponse(
                entity.getId(),
                entity.getCourseName(),
                professorName);
    }

}
