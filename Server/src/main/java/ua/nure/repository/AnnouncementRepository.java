package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.model.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, String> {

}
