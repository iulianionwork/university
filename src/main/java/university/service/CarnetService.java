package university.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.carnet.CarnetResponse;
import university.mapper.CarnetMapper;
import university.exception.NotFoundException;
import university.model.Carnet;
import university.model.Student;
import university.repository.CarnetRepository;
import university.repository.StudentRepository;
import university.service.component.StudentCodeGenerator;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarnetService {

    private final CarnetRepository carnetRepository;
    private final StudentRepository studentRepository;
    private final CarnetMapper carnetMapper;
    private final UserService userService;
    private final StudentCodeGenerator studentCodeGenerator;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public CarnetResponse issueCarnet(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found: " + studentId));

        if(student.getCarnet() != null){
            throw new IllegalStateException("Student already has a carnet!");
        }
        Carnet carnet = new Carnet(student, studentCodeGenerator.generate(studentId));
        carnetRepository.save(carnet);

        return carnetMapper.toDto(carnet);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('STUDENT')")
    public Optional<CarnetResponse> myCarnet(){
        Student student = userService.getStudent();
        return carnetRepository.findCarnetByStudentId(student.getId()).map(carnetMapper::toDto);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<CarnetResponse> findCarnetByStudentId(Long studentId){
        return carnetRepository.findCarnetByStudentId(studentId)
                .map(carnetMapper::toDto);
    }
}
