package com.example.backend.controller;

import com.example.backend.TestSecurityConfig;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = UserController.class,
    excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration.class
    }
)
@AutoConfigureMockMvc(addFilters = false) 
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        User u1 = new User();
        u1.setName("Alice");
        User u2 = new User();
        u2.setName("Bob");

        when(userService.getAllUsers()).thenReturn(List.of(u1, u2));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void testGetUserById_Found() throws Exception {
        User user = new User();
        user.setName("Charlie");

        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Charlie"));
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("David");
        user.setEmail("david@example.com");

        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        String json = """
                {"name":"David","email":"david@example.com"}
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("David"));
    }
    @Test
    void testCreateUser_Failure() throws Exception {
        when(userService.createUser(Mockito.any(User.class)))
                .thenThrow(new RuntimeException("Email exists"));

        String json = """
                {"name":"David","email":"david@example.com"}
                """;

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void testGetUserById_NotFound() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllUsers_Empty() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of());

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }



}
