package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
