package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
