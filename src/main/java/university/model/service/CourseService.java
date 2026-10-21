package university.model.service;

import org.springframework.transaction.annotation.Transactional;
import university.model.entity.Course;
import university.model.entity.Student;

import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course getCourseByName(String name);
    Course createCourse(String name, Long professorId);
    Course updateNameOfCourse(Long id, String name);
    void assignProfessorToCourse(Long courseId, Long professorId);
    void enrollStudentToCourse(Long courseId, Long studentId);
    void unenrollStudentToCourse(Long courseId, Long studentId);
    void deleteCourse(Long id);
    List<Course> getCoursesOfProfessor();
    List<Student> getStudentsByCourseId(Long courseId);
    List<Course> getCoursesOfStudent();
}
