package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseByCourseName(String name);

}
