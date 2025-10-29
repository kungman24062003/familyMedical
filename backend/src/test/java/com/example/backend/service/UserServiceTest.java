package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User saved = userService.createUser(user);
        assertEquals("test@example.com", saved.getEmail());
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        User user = new User();
        user.setEmail("duplicate@example.com");

        when(userRepository.findByEmail("duplicate@example.com")).thenReturn(Optional.of(user));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.createUser(user));
        assertEquals("Email is already registered!", ex.getMessage());
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }
}
