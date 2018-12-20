package ua.nure.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.model.Announcement;

@Repository
public interface AnnouncementRepository extends CrudRepository<Announcement, String> {

}
