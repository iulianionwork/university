package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Role;
import university.model.entity.UserCredentials;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserCredentials, Long> {

    Optional<UserCredentials> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);
    List<UserCredentials> findByRole(Role role);
}
