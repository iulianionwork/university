package university.model.repository;

import org.springframework.data.repository.CrudRepository;
import university.model.entity.Carnet;

public interface CarnetRepository  extends CrudRepository<Carnet, Long> {
}
