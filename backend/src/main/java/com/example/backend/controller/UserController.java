package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils; // Inject JwtUtils

    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }
    
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("No token provided");
        }
        
        String token = authHeader.substring(7);
        
        try {
            // Validate token using JwtUtils
            if (!jwtUtils.isTokenValid(token)) {
                return ResponseEntity.status(401).body("Invalid token");
            }
            
            // Extract claims
            var parser = Jwts.parserBuilder()
                .setSigningKey(jwtUtils.getSecretKey()) // Need to add this method
                .build();
            
            Claims claims = parser.parseClaimsJws(token).getBody();
            
            Integer userId = claims.get("id", Integer.class);
            
            if (userId == null) {
                return ResponseEntity.badRequest().body("No user ID in token");
            }
            
            Optional<User> userOpt = userService.getUserById(userId);
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(404).body("User not found");
            }
            
            return ResponseEntity.ok(userOpt.get());
            
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token: " + e.getMessage());
        }
    }
}