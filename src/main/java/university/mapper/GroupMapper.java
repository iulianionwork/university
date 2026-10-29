package university.mapper;

import org.springframework.stereotype.Component;
import university.dto.group.GroupRequest;
import university.dto.group.GroupResponse;
import university.model.Group;

@Component
public class GroupMapper implements ToDtoMapper<Group, GroupResponse>, ToEntityMapper<GroupRequest, Group>, UpdateMapper<GroupRequest, Group> {

    @Override
    public GroupResponse toDto(Group dto) {
        return new GroupResponse(
                dto.getId(),
                dto.getGroupName(),
                dto.getStudents() == null ? 0 : dto.getStudents().size() );
    }

    @Override
    public Group toEntity(GroupRequest dto) {
        return new Group(dto.groupName());
    }


    @Override
    public void updateEntity(GroupRequest dto, Group entity) {
        entity.setGroupName(dto.groupName());
    }
}
