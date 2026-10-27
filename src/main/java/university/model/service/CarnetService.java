package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import university.model.repository.CarnetRepository;

@Service
@AllArgsConstructor
public class CarnetService {

    private final CarnetRepository carnetRepository;
}
