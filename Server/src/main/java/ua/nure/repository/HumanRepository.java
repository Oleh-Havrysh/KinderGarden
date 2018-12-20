package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Human;

import java.util.List;

@Repository
public interface HumanRepository extends CrudRepository<Human, String> {
    Human findByEmail(@Param("email") String email);

    Human findByLogin(@Param("login") String login);

    List<Human> findByRole(@Param("role") int role);
}
