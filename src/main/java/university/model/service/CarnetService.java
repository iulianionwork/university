package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.carnet.CarnetResponse;
import university.controller.mapper.CarnetMapper;
import university.exception.NotFoundException;
import university.model.entity.Carnet;
import university.model.entity.Student;
import university.model.repository.CarnetRepository;
import university.model.repository.StudentRepository;

@Service
@AllArgsConstructor
public class CarnetService {

    private final CarnetRepository carnetRepository;
    private final StudentRepository studentRepository;
    private final CarnetMapper carnetMapper;
    private final UserService userService;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CarnetResponse issueCarnet(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
        Carnet carnet = new Carnet(student);
        carnetRepository.save(carnet);

        return carnetMapper.toDto(carnet);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('STUDENT')")
    public CarnetResponse myCarnet(){
        Student student = userService.getStudent();
        Carnet carnet = carnetRepository.findCarnetByStudentId(student.getId());

        return carnetMapper.toDto(carnet);
    }
}
