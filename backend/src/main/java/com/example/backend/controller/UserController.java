package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.backend.dto.LoginRequest;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public UserController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login/patient")
    public ResponseEntity<?> loginPatient(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.loginPatient(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<User>> getAllDoctors() {
        return ResponseEntity.ok(userService.getAllDoctors());
    }


}
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        try {
            // Check token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("No token provided");
            }

            String token = authHeader.substring(7);

            // Parse JWT
            var parser = Jwts.parserBuilder()
                    .setSigningKey(jwtUtils.getSecretKey()) // bạn cần có hàm getSecretKey() trong JwtUtils
                    .build();

            Claims claims = parser.parseClaimsJws(token).getBody();

            Integer userId = claims.get("id", Integer.class);

            if (userId == null) {
                return ResponseEntity.badRequest().body("No user ID found in token");
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
