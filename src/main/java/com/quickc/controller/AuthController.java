package com.quickc.controller;

import com.quickc.model.User;
import com.quickc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password) {
        User user = authService.login(email, password);
        if (user != null) {
            // Set session or redirect to home
            return "redirect:/";
        }
        return "login";  // Error
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        authService.register(user);
        return "redirect:/login";
    }
}
