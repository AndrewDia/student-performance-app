package ua.kpi.architecture.service;

import org.springframework.stereotype.Service;
import ua.kpi.architecture.domain.Group;
import ua.kpi.architecture.repository.GroupRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findGroupByName(String name) {
        return groupRepository.findGroupByName(name);
    }
}
