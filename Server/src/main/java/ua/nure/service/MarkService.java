package ua.nure.service;

import ua.nure.model.Child;
import ua.nure.model.Mark;

import java.util.List;

public interface MarkService {
    Mark findById(String id);

    void save(Mark mark);

    List<Mark> findByChild(Child child);
}
