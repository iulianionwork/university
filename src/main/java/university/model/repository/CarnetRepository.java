package university.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import university.model.entity.Carnet;

    public interface CarnetRepository  extends JpaRepository<Carnet, Long> {
}
