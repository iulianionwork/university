package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import university.model.entity.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User getUsersById(Long userId);

}
