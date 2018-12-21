package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;
import ua.nure.repository.ChildRepository;
import ua.nure.service.ChildService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChildServiceImpl implements ChildService {

    @Autowired
    private ChildRepository childRepository;

    @Override
    public List<Child> findByGroup(Group group) {
        return childRepository.findByGroup(group);
    }

    @Override
    public List<Child> findAll() {
        List<Child> result = new ArrayList<>();
        childRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public List<Child> findByParent(Human parent) {
        return childRepository.findByParent(parent);
    }

    @Override
    public Child findById(String id) {
        return childRepository.findById(id).orElse(null);
    }

    /**
     * Save a child.
     *
     * @param child the entity to save
     * @return the persisted entity
     */
    @Override
    public Child save(Child child) {
        return childRepository.save(child);
    }

    /**
     * Get all the children.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Child> findAll(Pageable pageable) {
        return childRepository.findAll(pageable);
    }

    /**
     * Delete the child by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        childRepository.deleteById(id);
    }
}
