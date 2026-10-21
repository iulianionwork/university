package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Professor;

@Repository
public interface ProfessorRepository  extends JpaRepository<Professor, Long> {
}
