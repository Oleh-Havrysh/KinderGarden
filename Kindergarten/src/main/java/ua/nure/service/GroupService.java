package ua.nure.service;

import ua.nure.model.Group;
import ua.nure.model.Human;

import java.util.List;

public interface GroupService {
    List<Group> findAll();

    Group findById(String id);

    List<Group> findByTeacher(Human teacher);
}
