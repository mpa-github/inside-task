package com.mpa.insidetask.domain.mappers;

import com.mpa.insidetask.domain.dto.SignUpRequestDTO;
import com.mpa.insidetask.domain.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(SignUpRequestDTO request) {
        User user = new User();
        user.setName(request.getName().toLowerCase());
        user.setPassword(request.getPassword());
        return user;
    }
}
