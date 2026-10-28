package university.dto.group;

public record GroupResponse(
        Long id,
        String groupName,
        int studentCount) {
}
