package com.example.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import com.example.backend.dto.LoginRequest;
import com.example.backend.dto.LoginResponse;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered!");
        }

        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public LoginResponse loginPatient(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email không tồn tại!"));

        // Kiểm tra role
        if (!user.getRole().equals(User.Role.patient)) {
            throw new RuntimeException("Tài khoản này không phải role patient!");
        }

        // Kiểm tra mật khẩu
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Sai mật khẩu!");
        }

        // Trả response (token demo)
        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getRole().name(),
                "patient-token-" + user.getId()
        );
        
    }
    public List<User> getAllDoctors() {
        return userRepository.findAllByRole(User.Role.doctor);
    }
}
