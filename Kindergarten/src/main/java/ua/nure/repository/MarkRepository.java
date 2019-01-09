package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Child;
import ua.nure.model.Mark;
import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, String> {
    List<Mark> findByChild(@Param("child") Child child);
}
