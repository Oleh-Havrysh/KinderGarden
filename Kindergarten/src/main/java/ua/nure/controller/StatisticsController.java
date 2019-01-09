package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Child;
import ua.nure.model.Mark;
import ua.nure.model.SensorData;
import ua.nure.service.ChildService;
import ua.nure.service.MarkService;
import ua.nure.service.SensorDataService;

import java.util.List;

@RestController
public class StatisticsController {
    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private MarkService markService;
    @Autowired
    private ChildService childService;

    @PostMapping("/sensors")
    public void saveSensorData(@RequestBody SensorData data) {
        sensorDataService.save(data);
    }

    @GetMapping("/marks/{id}")
    public Mark getMark(@PathVariable("id") String markId) {
        return markService.findById(markId);
    }

    @GetMapping("/marksByChild/{id}")
    public List<Mark> getMarksByChild(@PathVariable("id") String childId) {
        Child child = childService.findById(childId);
        return markService.findByChild(child);
    }

    @PostMapping(value = "/saveMark")
    public void saveMark(@RequestBody Mark mark) {
        markService.save(mark);
    }
}

