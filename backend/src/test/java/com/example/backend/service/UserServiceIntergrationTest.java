package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceIntergrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndRetrieveUser() {
        User user = new User();
        user.setEmail("integration@example.com");
        user.setName("Integration Test");

        User saved = userService.createUser(user);
        Optional<User> found = userService.getUserById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("integration@example.com", found.get().getEmail());
        assertEquals("Integration Test", found.get().getName());
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setRole(User.Role.patient);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        user2.setRole(User.Role.patient);
        userRepository.save(user2);

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());

    }
}
