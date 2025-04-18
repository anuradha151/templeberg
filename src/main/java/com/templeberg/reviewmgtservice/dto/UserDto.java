package com.templeberg.reviewmgtservice.dto;

import com.templeberg.reviewmgtservice.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
}
