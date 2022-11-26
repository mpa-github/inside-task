package com.mpa.insidetask.controllers;

import com.mpa.insidetask.domain.dto.JwtTokenDTO;
import com.mpa.insidetask.domain.dto.SignUpRequestDTO;
import com.mpa.insidetask.domain.mappers.UserMapper;
import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService,
                          UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/auth/signup")
    public JwtTokenDTO registerNewUser(@RequestBody SignUpRequestDTO signUpDTO) {
        User user = userMapper.toUser(signUpDTO);
        userService.register(user);
        //TODO Add JWT Functionality
        return new JwtTokenDTO(String.format("JSON Web Token for user: '%s'", user.getName()));
    }
}
