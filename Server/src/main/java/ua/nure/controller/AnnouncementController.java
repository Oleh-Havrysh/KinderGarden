package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Announcement;
import ua.nure.service.AnnouncementService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/announcements")
    public List<Announcement> getAllAnnouncements() {
        return announcementService.findAll();
    }

    @GetMapping("/announcements/{id}")
    public Announcement getAnnouncement(@PathVariable("id") String id) {
        return announcementService.findById(id);
    }

    @PostMapping("/saveEvent")
    @ResponseStatus(HttpStatus.OK)
    public void saveUser(@Valid @RequestBody Announcement announcement) {
        announcementService.save(announcement);
    }
}
