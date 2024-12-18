package com.example.eshop.tests.controller;

import com.example.eshop.db.services.UserService;
import com.example.eshop.rest.controllers.UserController;
import com.example.eshop.rest.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllUsers() throws Exception {
        // Mock service response
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(user));

        // Perform GET request
        mockMvc.perform(get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }

    @Test
    void shouldCreateUser() throws Exception {
        // Mock input and output
        UserDTO userRequest = new UserDTO();
        userRequest.setEmail("jane.doe@example.com");
        userRequest.setFirstName("Jane");
        userRequest.setLastName("Doe");

        UserDTO userResponse = new UserDTO();
        userResponse.setId(2L);
        userResponse.setEmail("jane.doe@example.com");
        userResponse.setFirstName("Jane");
        userResponse.setLastName("Doe");

        Mockito.when(userService.create(Mockito.any())).thenReturn(userResponse);

        // Perform POST request
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
}
