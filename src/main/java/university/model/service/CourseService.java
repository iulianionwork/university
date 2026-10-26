package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.CourseRequest;
import university.controller.dto.CourseUpdate;
import university.controller.mapper.CourseMapper;
import university.controller.mapper.ProfessorMapper;
import university.model.entity.Course;
import university.model.entity.Professor;
import university.model.entity.Student;
import university.model.entity.UserCredentials;
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
    private final CourseMapper courseMapper;




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
    public Course createCourse(CourseRequest request) {
        professorRepository.findById(request.professor().getId())
                .orElseThrow(() -> new RuntimeException("Professor not found: " + request.professor().getId()));

        Course course = courseMapper.toEntity(request);
        courseRepository.save(course);
        return course;

    }


    @Transactional
    public Course updateCourseProfessor(CourseUpdate courseUpdate, Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));
        courseMapper.updateEntity(courseUpdate, course);
        return course;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials user = (UserCredentials) authentication.getPrincipal();
        Professor professor = user.getProfessor();

        return courseRepository.getCoursesByProfessorId(professor.getId());
    }


    @Transactional(readOnly = true)
    public List<Student> getStudentsByCourseId(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        return course.getStudents();
    }



    @Transactional(readOnly = true)
    public List<Course> getCoursesOfStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserCredentials user = (UserCredentials) authentication.getPrincipal();
        Student student = user.getStudent();

        return courseRepository.getCoursesByStudentId(student.getId());
    }


}
