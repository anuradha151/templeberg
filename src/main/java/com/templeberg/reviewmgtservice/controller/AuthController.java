package com.templeberg.reviewmgtservice.controller;

import com.templeberg.reviewmgtservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          Model model) {
        if (authService.login(username, password)) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "Login";
        }
    }
}
