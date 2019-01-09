package ua.nure.service;

import ua.nure.model.Child;
import ua.nure.model.SensorData;

import java.util.List;

public interface SensorDataService {
    void save(SensorData data);

    List<SensorData> findByChild(Child child);
}
