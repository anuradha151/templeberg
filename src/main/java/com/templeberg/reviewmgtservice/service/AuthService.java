package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationStateService applicationStateService;

    public boolean login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .map(user -> {
                    applicationStateService.setLoggedUser(user);
                    return true;
                })
                .orElse(false);
    }
}
