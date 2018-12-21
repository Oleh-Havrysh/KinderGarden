package ua.nure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.nure.model.Group;
import ua.nure.model.Human;

import java.util.List;

public interface GroupService {
    List<Group> findAll();

    /**
     * Get the "id" gardenGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Group findById(String id);

    List<Group> findByTeacher(Human teacher);

    /**
     * Save a gardenGroup.
     *
     * @param gardenGroup the entity to save
     * @return the persisted entity
     */
    Group save(Group gardenGroup);

    /**
     * Get all the gardenGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Group> findAll(Pageable pageable);

    /**
     * Delete the "id" gardenGroup.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
