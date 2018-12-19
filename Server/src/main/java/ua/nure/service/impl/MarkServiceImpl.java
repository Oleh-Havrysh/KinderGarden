package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Child;
import ua.nure.model.Mark;
import ua.nure.repository.MarkRepository;
import ua.nure.service.MarkService;

import java.util.List;

@Service
@Transactional
public class MarkServiceImpl implements MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark findById(String id) {
        return markRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Mark mark) {
        markRepository.save(mark);
    }

    @Override
    public List<Mark> findByChild(Child child) {
        return markRepository.findByChild(child);
    }
}
