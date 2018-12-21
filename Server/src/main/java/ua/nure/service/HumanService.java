package ua.nure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.nure.model.Human;

import java.util.List;

public interface HumanService {
    /**
     * Get the "id" human.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Human findById(String id);

    List<Human> findByRole(int role);

    Human findByEmail(String email);

    void register(Human human);

    /**
     * Save a human.
     *
     * @param human the entity to save
     * @return the persisted entity
     */
    Human save(Human human);

    /**
     * Get all the parents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Human> findAllParents(Pageable pageable);

    /**
     * Get all the teachers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Human> findAllTeachers(Pageable pageable);

    /**
     * Delete the "id" parent.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
