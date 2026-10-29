package university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
