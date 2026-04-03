package com.quickc.controller;

import com.quickc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile(Model model) {
        // Fetch current user from session
        User user = null;
        model.addAttribute("user", user);
        return "profile";
    }
}
