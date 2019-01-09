package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Group;
import ua.nure.model.Human;
import ua.nure.repository.GroupRepository;
import ua.nure.service.GroupService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findAll() {
        List<Group> result = new ArrayList<>();
        groupRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Group findById(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public List<Group> findByTeacher(Human teacher) {
        return groupRepository.findByTeacher(teacher);
    }
}
