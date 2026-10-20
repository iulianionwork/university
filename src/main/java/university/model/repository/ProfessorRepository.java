package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import university.model.entity.Professor;

public interface ProfessorRepository  extends JpaRepository<Professor, Long> {
}
