package ua.nure.service;

import ua.nure.model.Human;

import java.util.List;

public interface HumanService {
    Human findById(String id);

    List<Human> findByRole(int role);

    void save(Human human);

    Human findByEmail(String email);

    Human findByLogin(String login);

    void register(Human human);
}
