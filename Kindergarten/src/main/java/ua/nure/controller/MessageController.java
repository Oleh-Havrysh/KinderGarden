package ua.nure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.model.Human;
import ua.nure.model.Message;
import ua.nure.service.HumanService;
import ua.nure.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private HumanService humanService;

    @PostMapping("/saveMessage")
    @ResponseStatus(HttpStatus.OK)
    public void saveMessage(@Valid @RequestBody Message message) {
        messageService.save(message);
    }

    @GetMapping("/messages/{id}")
    public Message getMessage(@PathVariable("id") String id) {
        return messageService.findById(id);
    }

    @GetMapping("/messagesByUser/{id}")
    public List<Message> getMessagesByUser(@PathVariable("id") String id) {
        Human human = humanService.findById(id);
        return messageService.findMessagesByReceiver(human);
    }
}

