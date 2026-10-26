package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.course.CourseRequest;
import university.controller.dto.course.CourseResponse;
import university.controller.dto.course.CourseUpdate;
import university.controller.dto.student.StudentResponse;
import university.controller.mapper.CourseMapper;
import university.controller.mapper.ProfessorMapper;
import university.controller.mapper.StudentMapper;
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
    private final CourseMapper courseMapper;
    private final ProfessorMapper professorMapper;
    private final StudentMapper studentMapper;
    private final UserService userService;



    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public CourseResponse getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));
        return courseMapper.toDto(course);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public CourseResponse getCourseByName(String name) {
        Course course = courseRepository.getCourseByCourseName(name);
        if(course == null){
            throw new RuntimeException("Course not found: " + name);
        }
        return courseMapper.toDto(course);
    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CourseResponse createCourse(CourseRequest request) {
        Professor professor = professorRepository.findById(request.professorId())
                .orElseThrow(() -> new RuntimeException("Professor not found: " + request.professorId()));

        Course course = new Course(request.courseName(), professor);
        courseRepository.save(course);
        return courseMapper.toDto(course);

    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CourseResponse updateCourseProfessor(CourseUpdate courseUpdate, Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));
        Professor professor = professorRepository.findById(courseUpdate.professorId())
                .orElseThrow(() -> new RuntimeException("Professor not found: " + courseUpdate.professorId()));

        course.setProfessor(professor);
        courseRepository.save(course);
        return courseMapper.toDto(course);
    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found: " + id));
        courseRepository.delete(course);

    }



    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('PROFESSOR')")
    public List<CourseResponse> getCoursesOfProfessor() {
        Professor professor = userService.getProfessor();

        return courseRepository.getCoursesByProfessorId(professor.getId()).stream()
                .map(courseMapper::toDto)
                .toList();
    }


    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('PROFESSOR')")
    public List<StudentResponse> getStudentsByCourseId(Long courseId) {
        courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found: " + courseId));

        return userRepository.getStudentsByCourseId(courseId).stream()
                .map(studentMapper::toDto)
                .toList();
    }



    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('STUDENT')")
    public List<CourseResponse> getCoursesOfStudent() {
        Student student = userService.getStudent();
        return courseRepository.getCoursesByStudentId(student.getId()).stream()
                .map(courseMapper::toDto)
                .toList();
    }


}
