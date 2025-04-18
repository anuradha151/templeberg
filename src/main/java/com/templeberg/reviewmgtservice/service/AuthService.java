package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public boolean login(String username, String password) {

        userRepository.findByUsername(username).ifPresent(user -> {

        });

    }
}
