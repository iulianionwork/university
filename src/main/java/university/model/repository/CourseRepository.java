package university.model.repository;

import org.springframework.data.repository.CrudRepository;
import university.model.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
