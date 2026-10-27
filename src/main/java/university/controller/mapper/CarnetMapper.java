package university.controller.mapper;

import org.springframework.stereotype.Component;
import university.controller.dto.carnet.CarnetResponse;
import university.model.entity.Carnet;

@Component
public class CarnetMapper implements ToDtoMapper<Carnet, CarnetResponse> {
    @Override
    public CarnetResponse toDto(Carnet entity) {
        return new CarnetResponse(entity.getId(),
                entity.getStudentCode(),
                entity.getIssueDate());
    }
}
