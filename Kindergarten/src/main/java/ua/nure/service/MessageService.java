package ua.nure.service;

import ua.nure.model.Human;
import ua.nure.model.Message;

import java.util.List;

public interface MessageService {
    void save(Message message);

    List<Message> findMessagesByReceiver(Human receiver);

    Message findById(String id);
}
