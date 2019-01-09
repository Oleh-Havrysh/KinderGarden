package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Human;
import ua.nure.repository.HumanRepository;
import ua.nure.service.HumanService;

import java.util.List;

@Service
@Transactional
public class HumanServiceImpl implements HumanService {

    @Autowired
    private HumanRepository humanRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Human findById(String id) {
        return humanRepository.findById(id).orElse(null);
    }

    @Override
    public List<Human> findByRole(int role) {
        return humanRepository.findByRole(role);
    }

    @Override
    public void save(Human human) {
        humanRepository.save(human);
    }

    @Override
    public Human findByEmail(String email) {
        return humanRepository.findByEmail(email);
    }

    public void register(Human human) {
        human.setPassword(passwordEncoder.encode(human.getPassword()));
        save(human);
    }

    @Override
    public Human findByLogin(String login) {
        return humanRepository.findByLogin(login);
    }
}
