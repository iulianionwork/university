package university.model.service;

import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.dto.professor.CreateProfessorRequest;
import university.dto.student.CreateStudentRequest;


@Service
@AllArgsConstructor
public class FakeDataService {

    private final StudentService studentService;
    private final ProfessorService professorService;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void generateStudents(int count) {
        Faker faker = new Faker();

        for (int i = 1; i <= count; i++) {

            CreateStudentRequest request =
                    new CreateStudentRequest(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            "student"
                    );

            studentService.create(request);
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void generateMateProfessor(int count) {
        Faker faker = new Faker();

        for (int i = 1; i <= count; i++) {

            CreateProfessorRequest request =
                    new CreateProfessorRequest(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            "professor",
                            "Mathematica"
                    );

            professorService.create(request);
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void generateInfoProfessor(int count) {
        Faker faker = new Faker();

        for (int i = 1; i <= count; i++) {

            CreateProfessorRequest request =
                    new CreateProfessorRequest(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            "professor",
                            "Informatica"
                    );

            professorService.create(request);
        }
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void generateItalianProfessor(int count) {
        Faker faker = new Faker();

        for (int i = 1; i <= count; i++) {

            CreateProfessorRequest request =
                    new CreateProfessorRequest(
                            faker.name().firstName(),
                            faker.name().lastName(),
                            "professor",
                            "Italian"
                    );

            professorService.create(request);
        }
    }
}
