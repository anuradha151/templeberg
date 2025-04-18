package com.templeberg.reviewmgtservice.service;

import com.templeberg.reviewmgtservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class ApplicationStateService {
    private User loggedUser;
}
