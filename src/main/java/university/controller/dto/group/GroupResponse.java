package university.controller.dto.group;

public record GroupResponse(
        Long id,
        String groupName,
        int studentCount) {
}
