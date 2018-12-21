package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Child;
import ua.nure.model.Group;
import ua.nure.model.Human;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, String> {
    List<Child> findByGroup(@Param("group") Group group);

    List<Child> findByParent(@Param("parent") Human parent);
}
