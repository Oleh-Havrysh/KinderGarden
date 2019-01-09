package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Child;
import ua.nure.model.Mark;

import java.sql.Date;
import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, String> {
    List<Mark> findByChild(@Param("child") Child child);

    List<Mark> findByChildAndDate(@Param("child") Child child, @Param("date") Date date);
}
