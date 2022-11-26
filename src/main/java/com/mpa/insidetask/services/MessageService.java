package com.mpa.insidetask.services;

import com.mpa.insidetask.domain.models.Message;
import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.repositories.MessageRepository;
import com.mpa.insidetask.security.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveUserMessage(User user, Message message) {
        message.setUser(user);
        messageRepository.save(message);
    }

    public List<Message> getLastUserMessages(User user, int limit) {
        if (limit <= 0) {
            throw new BadRequestException("Parameter 'limit' must be more than zero!");
        }
        Page<Message> page = messageRepository.findAllByUser(user, PageRequest.of(
                0, limit, Sort.by(Sort.Order.desc("id"))));
        return page.getContent();
    }
}
