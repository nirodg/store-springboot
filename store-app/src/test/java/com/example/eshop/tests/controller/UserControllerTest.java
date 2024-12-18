package com.example.eshop.tests.controller;

import com.example.eshop.db.entities.User;
import com.example.eshop.db.services.CartService;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.db.services.UserService;
import com.example.eshop.rest.controllers.UserController;
import com.example.eshop.rest.mappers.UserMapper;
import com.example.eshop.rest.model.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@Import(UserControllerTest.MockConfig.class) // Import mocked configuration
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void shouldGetAllUsers() throws Exception {
        // JPA Entity to simulate database object
        User userEntity = new User();
        userEntity.setId(1L);
        userEntity.setEmail("john.doe@example.com");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");

        // DTO to validate the API response
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("john.doe@example.com");
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");

        // Mock service and mapper behavior
        Mockito.when(userService.findAll()).thenReturn(Collections.singletonList(userEntity));
        Mockito.when(userMapper.toDTO(userEntity)).thenReturn(userDTO);

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
        // DTO for request
        UserDTO userRequest = new UserDTO();
        userRequest.setEmail("jane.doe@example.com");
        userRequest.setFirstName("Jane");
        userRequest.setLastName("Doe");

        // JPA Entity to simulate database object
        User userEntity = new User();
        userEntity.setId(2L);
        userEntity.setEmail("jane.doe@example.com");
        userEntity.setFirstName("Jane");
        userEntity.setLastName("Doe");

        // DTO for response
        UserDTO userResponse = new UserDTO();
        userResponse.setId(2L);
        userResponse.setEmail("jane.doe@example.com");
        userResponse.setFirstName("Jane");
        userResponse.setLastName("Doe");

        // Mock service and mapper behavior
        Mockito.when(userMapper.toEntity(userRequest)).thenReturn(userEntity);
        Mockito.when(userService.create(userEntity)).thenReturn(userEntity);
        Mockito.when(userMapper.toDTO(userEntity)).thenReturn(userResponse);

        // Perform POST request
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.firstName").value("Jane"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public UserService userService() {
            return Mockito.mock(UserService.class);
        }

        @Bean
        public UserMapper userMapper() {
            return Mockito.mock(UserMapper.class);
        }

        // Mock any additional beans used by UserService or UserController
        @Bean
        public OrderService orderService() {
            return Mockito.mock(OrderService.class);
        }

        @Bean
        public CartService cartService() {
            return Mockito.mock(CartService.class);
        }
    }

}
