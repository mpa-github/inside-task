package com.mpa.insidetask.controllers;

import com.mpa.insidetask.domain.dto.MessageDTO;
import com.mpa.insidetask.domain.mappers.MessageMapper;
import com.mpa.insidetask.domain.models.Message;
import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.services.MessageService;
import com.mpa.insidetask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class MessageController {

    private static final String REGEX_MESSAGE_HISTORY = "history [1-9]\\d*";
    private static final int PARAM_BEGIN_INDEX = 8;
    private final MessageService messageService;
    private final UserService userService;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageController(MessageService messageService,
                             UserService userService,
                             MessageMapper messageMapper) {
        this.messageService = messageService;
        this.userService = userService;
        this.messageMapper = messageMapper;
    }

    @PostMapping("/user/message")
    public String postMessage(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody MessageDTO messageDTO,
                              HttpServletResponse response) throws IOException {
        Message newMessage = messageMapper.toMessage(messageDTO);
        String text = newMessage.getText();
        if (text.matches(REGEX_MESSAGE_HISTORY)) {
            int paramValue = Integer.parseInt(text.substring(PARAM_BEGIN_INDEX));
            String url = String.format("http://localhost:3000/api/user/messages?limit=%d", paramValue);
            response.sendRedirect(url);
            return "Request was redirected.";
        }
        User user = userService.findUser(userDetails.getUsername());
        messageService.saveUserMessage(user, newMessage);
        return String.format("A message from '%s' has been posted.", user.getName());
    }

    @PostMapping("/user/messages")
    public List<MessageDTO> getMessages(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam(name = "limit", required = true) int limit) {
        User user = userService.findUser(userDetails.getUsername());
        List<Message> messages = messageService.getLastUserMessages(user, limit);
        List<MessageDTO> dtoList = new ArrayList<>(messages.size());
        messages.forEach(message -> dtoList.add(messageMapper.toMessageDto(message)));
        return dtoList;
    }
}
