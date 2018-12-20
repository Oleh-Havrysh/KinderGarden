package ua.nure.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Human;

import java.util.List;

@Repository
public interface HumanRepository extends JpaRepository<Human, String> {
    Human findByEmail(@Param("email") String email);

    List<Human> findByRole(@Param("role") int role);

    Page<Human> findAllByRole(Pageable pageable, @Param("role") int role);
}
