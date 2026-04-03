package com.quickc.service;

import com.quickc.model.User;
import com.quickc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;  // For hashing
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public void register(User user) {
        // Hash password before saving
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.saveUser(user);  // Save to Firebase realtime
    }

    public User login(String email, String password) {
        User user = userRepo.findByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())) {
            // Successful login
            return user;
        }
        return null;
    }
}
