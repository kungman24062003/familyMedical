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

    @Test
    void testGetUserById_Found() {
        User user = new User();
        user.setId(1);
        user.setEmail("found@example.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals("found@example.com", result.get().getEmail());
    }


    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(99);
        assertFalse(result.isPresent());
    }


    @Test
    void testGetUserByEmail_Found() {
        User user = new User();
        user.setEmail("hello@example.com");

        when(userRepository.findByEmail("hello@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByEmail("hello@example.com");
        assertTrue(result.isPresent());
        assertEquals("hello@example.com", result.get().getEmail());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        when(userRepository.findByEmail("none@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserByEmail("none@example.com");
        assertFalse(result.isPresent());
    }
}
