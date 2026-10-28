package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Carnet;

import java.util.Optional;

@Repository
public interface CarnetRepository  extends JpaRepository<Carnet, Long> {
    Optional<Carnet> findCarnetByStudentId(Long studentId);
}
