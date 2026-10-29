package university.mapper;

import org.springframework.stereotype.Component;
import university.dto.carnet.CarnetResponse;
import university.model.Carnet;

@Component
public class CarnetMapper implements ToDtoMapper<Carnet, CarnetResponse> {
    @Override
    public CarnetResponse toDto(Carnet entity) {
        return new CarnetResponse(entity.getId(),
                entity.getStudentCode(),
                entity.getIssueDate());
    }
}
