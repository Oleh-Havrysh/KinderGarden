package ua.nure.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;

import java.util.List;

public interface ChildService {
    List<Child> findByGroup(Group group);

    List<Child> findAll();

    List<Child> findByParent(Human parent);

    /**
     * Get the "id" child.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Child findById(String id);

    /**
     * Save a child.
     *
     * @param child the entity to save
     * @return the persisted entity
     */
    Child save(Child child);

    /**
     * Get all the children.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Child> findAll(Pageable pageable);

    /**
     * Delete the "id" child.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
