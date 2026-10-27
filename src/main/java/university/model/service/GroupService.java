package university.model.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import university.controller.dto.group.GroupRequest;
import university.controller.dto.group.GroupResponse;
import university.controller.dto.student.StudentResponse;
import university.controller.mapper.GroupMapper;
import university.controller.mapper.StudentMapper;
import university.exception.NotFoundException;
import university.model.entity.Group;
import university.model.repository.GroupRepository;
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
    @PreAuthorize("hasRole('ADMIN')")
    public GroupResponse createGroup(GroupRequest dto) {
        Group group = groupRepository.save(groupMapper.toEntity(dto));
        return groupMapper.toDto(group);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<GroupResponse> getGroups() {
        return  groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentResponse> getStudentsFromGroup(Long groupId) {

        if(!groupRepository.existsById(groupId)) {
            throw new NotFoundException("Group not found: " + groupId);
        }
        return userRepository.getStudentsByGroupId(groupId).stream()
                .map(studentMapper::toDto)
                .toList();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGroup(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Group not found: " + groupId));

        group.getStudents().clear();
        groupRepository.delete(group);
    }



}
