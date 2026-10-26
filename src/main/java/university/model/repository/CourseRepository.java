package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Course;
import university.model.entity.Professor;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseByCourseName(String name);
    List<Course> getCoursesByProfessorId(Long professorId);
    List<Course> getCoursesByStudentId(Long studentId);

}
