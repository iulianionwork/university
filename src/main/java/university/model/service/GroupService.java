package university.model.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.GroupRequest;
import university.controller.dto.GroupResponse;
import university.controller.dto.StudentResponse;
import university.controller.mapper.GroupMapper;
import university.controller.mapper.StudentMapper;
import university.model.entity.Group;
import university.model.entity.Student;
import university.model.repository.GroupRepository;
import university.model.repository.StudentRepository;
import university.model.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    @Transactional
    public GroupResponse createGroup(GroupRequest dto) {
        Group group = groupRepository.save(groupMapper.toEntity(dto));
        return groupMapper.toDto(group);
    }

    @Transactional
    public GroupResponse updateGroup(GroupRequest dto, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group Id: " + groupId));

        group.setGroupName(dto.groupName());
        return groupMapper.toDto(group);

    }

    @Transactional(readOnly = true)
    public List<GroupResponse> getGroups() {
        return  groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsFromGroup(Long groupId) {

        if(!groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("Invalid group Id: " + groupId);
        }
        return userRepository.getStudentsByGroupId(groupId).stream()
                .map(studentMapper::toDto)
                .toList();

    }



}
