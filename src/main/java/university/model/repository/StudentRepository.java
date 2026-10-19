package university.model.repository;

import org.springframework.data.repository.CrudRepository;
import university.model.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
