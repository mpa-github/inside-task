package com.mpa.insidetask.services;

import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.repositories.UserRepository;
import com.mpa.insidetask.security.exceptions.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(User user) {
        if (userRepository.existsUserByNameIgnoreCase(user.getName())) {
            String message = String.format("User with name '%s' already exists!", user.getName());
            throw new UserAlreadyExistsException(message);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.findRole("ROLE_USER"));
        userRepository.save(user);
    }

    public User findUser(String userName) {
        return userRepository.findUserByNameIgnoreCase(userName);
    }

    public void grantRole(String userName, String roleName) {
        User user = userRepository.findUserByNameIgnoreCase(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: "  + userName);
        }
        user.getRoles().add(roleService.findRole(roleName));
        userRepository.save(user);
    }
}
