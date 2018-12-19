package ua.nure.service;

import ua.nure.model.Announcement;

import java.util.List;
public interface AnnouncementService {
    List<Announcement> findAll();

    Announcement findById(String id);

    void save(Announcement announcement);
}
