package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.dto.UserDto;
import com.templeberg.reviewmgtservice.model.User;
import com.templeberg.reviewmgtservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDto dto) {
        validateSaveRequest(dto);
        userRepository.findByEmail(dto.getEmail())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User already exists for the email: " + dto.getEmail());
                });

        userRepository.findByUsername(dto.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User already exists for the username: " + dto.getUsername());
                });

        userRepository.save(
                new User(
                        dto.getUsername(),
                        dto.getEmail(),
                        dto.getRole()
                )
        );

    }

    private void validateSaveRequest(UserDto dto) {
        if (dto.getUsername() == null || dto.getUsername().isEmpty())
            throw new IllegalArgumentException("Username cannot be empty");

        if (dto.getEmail() == null || dto.getEmail().isEmpty())
            throw new IllegalArgumentException("Email cannot be empty");

        if (dto.getRole() == null)
            throw new IllegalArgumentException("Role cannot be null");
    }

}
