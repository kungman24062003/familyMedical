package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
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