package university.model.repository;

import org.springframework.data.repository.CrudRepository;
import university.model.entity.Professor;

public interface ProfessorRepository  extends CrudRepository<Professor, Long> {
}
