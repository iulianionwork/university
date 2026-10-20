package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import university.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
