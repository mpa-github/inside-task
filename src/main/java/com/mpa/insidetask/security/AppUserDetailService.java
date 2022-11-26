package com.mpa.insidetask.security;

import com.mpa.insidetask.domain.models.User;
import com.mpa.insidetask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByNameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: "  + username);
        }
        return new AppUserDetails(user);
    }
}
