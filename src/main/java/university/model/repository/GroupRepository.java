package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import university.model.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
