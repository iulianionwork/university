package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import university.model.entity.Course;
import university.model.entity.Professor;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseByCourseName(String name);

}
