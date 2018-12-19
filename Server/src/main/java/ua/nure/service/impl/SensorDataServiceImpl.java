package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Child;
import ua.nure.model.SensorData;
import ua.nure.repository.SensorDataRepository;
import ua.nure.service.SensorDataService;

import java.util.List;

@Service
@Transactional
public class SensorDataServiceImpl implements SensorDataService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    public void save(SensorData data) {
        sensorDataRepository.save(data);
    }

    @Override
    public List<SensorData> findByChild(Child child) {
        return sensorDataRepository.findByChild(child);
    }
}
