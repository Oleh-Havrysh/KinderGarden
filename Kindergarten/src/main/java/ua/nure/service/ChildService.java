package ua.nure.service;

import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;

import java.util.List;

public interface ChildService {
    List<Child> findByGroup(Group group);

    List<Child> findAll();

    List<Child> findByParent(Human parent);

    Child findById(String id);

    void save(Child child);
}
