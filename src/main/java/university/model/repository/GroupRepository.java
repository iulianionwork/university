package university.model.repository;

import org.springframework.data.repository.CrudRepository;
import university.model.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Long> {
}
