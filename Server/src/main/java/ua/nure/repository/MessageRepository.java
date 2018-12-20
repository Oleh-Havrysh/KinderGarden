package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Human;
import ua.nure.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {
    List<Message> findByTo(@Param(value = "to") Human to);
}

