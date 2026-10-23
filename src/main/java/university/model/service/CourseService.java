package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.model.entity.Course;
import university.model.entity.Professor;
import university.model.entity.Student;
import university.model.repository.CourseRepository;
import university.model.repository.ProfessorRepository;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseService{

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;




    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found: " + id));
    }


    public Course getCourseByName(String name) {
        Course course = courseRepository.getCourseByCourseName(name);
        if(course == null){
            throw new RuntimeException("Course not found: " + name);
        }
        return course;
    }


    @Transactional
    public Course createCourse(String name, Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found: " + professorId));

        Course course = new Course(name, professor);
        courseRepository.save(course);
        return course;

    }


    @Transactional
    public Course updateNameOfCourse(Long id, String name) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found: " + id));
        course.setCourseName(name);
        return course;
    }


    @Transactional
    public void assignProfessorToCourse(Long courseId, Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found: " + professorId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        course.setProfessor(professor);

    }


    @Transactional
    public void enrollStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

        List<Course> courses =  student.getCourses();
        courses.add(course);
        student.setCourses(courses);


    }


    @Transactional
    public void unenrollStudentToCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));

        List<Course> courses =  student.getCourses();
        courses.remove(course);
        student.setCourses(courses);

    }


    @Transactional
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found: " + id));
        courseRepository.delete(course);

    }



    @Transactional(readOnly = true)
    public List<Course> getCoursesOfProfessor() {

        return List.of();
    }


    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        return course.getStudents();
    }



    @Transactional(readOnly = true)
    public List<Course> getCoursesOfStudent() {


        return List.of();
    }


}
