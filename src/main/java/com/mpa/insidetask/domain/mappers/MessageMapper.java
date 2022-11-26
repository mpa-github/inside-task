package com.mpa.insidetask.domain.mappers;

import com.mpa.insidetask.domain.dto.MessageDTO;
import com.mpa.insidetask.domain.models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {

    public Message toMessage(MessageDTO dto) {
        Message message = new Message();
        message.setId(null);
        message.setText(dto.getMessage());
        message.setUser(null);
        return message;
    }

    public MessageDTO toMessageDto(Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setName(message.getUser().getName());
        dto.setMessage(message.getText());
        return dto;
    }
}
