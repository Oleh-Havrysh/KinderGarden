package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Announcement;
import ua.nure.repository.AnnouncementRepository;
import ua.nure.service.AnnouncementService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public List<Announcement> findAll() {
        List<Announcement> result = new ArrayList<>();
        announcementRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Announcement findById(String id) {
        return announcementRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Announcement announcement) {
        announcementRepository.save(announcement);
    }
}
