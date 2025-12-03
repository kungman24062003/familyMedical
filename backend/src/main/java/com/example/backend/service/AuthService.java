package com.example.backend.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend.entity.User;
import com.example.backend.utils.JwtUtils;

@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    public User register(User user) {
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered!");
        }
        System.out.println("Password: " + user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    public Map<String, Object> login(String email, String password) {
        Optional<User> userOpt = userService.getUserByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("Invalid email or password!");
        }

        var user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        Map<String, Object> claims = Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "role", user.getRole());

        String token = jwtUtils.generateToken(claims);
        return Map.of(
                "user", Map.of(
                        "id", user.getId(),
                        "name", user.getName(),
                        "email", user.getEmail(),
                        "role", user.getRole()),
                "token", token,
                "message", "Login successful!");
    }

}
