package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Group;
import ua.nure.model.Human;
import ua.nure.repository.GroupRepository;
import ua.nure.service.GroupService;

import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findById(String id) {
        return groupRepository.findById(id).orElse(null);
    }

    @Override
    public List<Group> findByTeacher(Human teacher) {
        return groupRepository.findByTeacher(teacher);
    }

    /**
     * Save a Group.
     *
     * @param group the entity to save
     * @return the persisted entity
     */
    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    /**
     * Get all the Groups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Group> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    /**
     * Delete the Group by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        groupRepository.deleteById(id);
    }
}
