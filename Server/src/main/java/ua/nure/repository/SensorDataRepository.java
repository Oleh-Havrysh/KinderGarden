package ua.nure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.nure.model.Child;
import ua.nure.model.SensorData;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, String> {
    List<SensorData> findByChild(@Param(value = "child") Child child);
}
