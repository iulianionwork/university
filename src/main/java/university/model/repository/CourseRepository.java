package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import university.model.entity.Course;
import university.model.entity.Professor;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Course getCourseByCourseName(String name);
    List<Course> getCoursesByProfessorId(Long professorId);

    @Query("""
    SELECT c FROM Course c
    JOIN FETCH c.professor
    JOIN c.students s
    WHERE s.id =:studentId
    """)
    List<Course> getCoursesByStudentId(@Param("studentId") Long studentId);

}
