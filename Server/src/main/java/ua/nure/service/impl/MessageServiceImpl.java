package ua.nure.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.model.Human;
import ua.nure.model.Message;
import ua.nure.repository.MessageRepository;
import ua.nure.service.MessageService;

import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Message> findMessagesByReceiver(Human receiver) {
        return messageRepository.findByTo(receiver);
    }

    @Override
    public Message findById(String id) {
        return messageRepository.findById(id).orElse(null);
    }
}
