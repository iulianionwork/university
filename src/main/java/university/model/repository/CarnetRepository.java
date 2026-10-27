package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import university.model.entity.Carnet;

@Repository
public interface CarnetRepository  extends JpaRepository<Carnet, Long> {
    Carnet findCarnetByStudentId(Long studentId);
}
