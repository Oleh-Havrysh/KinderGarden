package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;
import ua.nure.repository.ChildRepository;
import ua.nure.service.ChildService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void save(Child child) {
        childRepository.save(child);
    }
}
