package university.controller.dto;

public record GroupResponse(
        Long id,
        String groupName,
        int studentCount) {
}
