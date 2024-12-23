package com.example.eshop.tests.controller;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.db.repositories.UserRepository;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import com.example.eshop.rest.model.enums.OrderStatusDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderMapper orderMapper;

    private User testUser;
    private Order testOrder;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();

        testUser = new User();
        testUser.setFirstName("Test User");
        testUser.setEmail("test@example.com");
        userRepository.save(testUser);

        testOrder = new Order();
        testOrder.setUser(testUser);
        testOrder.setStatus(OrderStatus.CREATED);
        testOrder.setTotalAmount(BigDecimal.valueOf(100.0));
        orderRepository.save(testOrder);
    }

    @Test
    void shouldGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/v1/users/" + testUser.getId() + "/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testOrder.getId()))
                .andExpect(jsonPath("$[0].status").value(testOrder.getStatus().name()))
                .andExpect(jsonPath("$[0].totalAmount").value(testOrder.getTotalAmount()));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        OrderDTO newOrderDTO = new OrderDTO();
        newOrderDTO.setStatus(OrderStatusDTO.CREATED);
        newOrderDTO.setTotalAmount(BigDecimal.valueOf(200.0));

        mockMvc.perform(post("/api/v1/users/" + testUser.getId() + "/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newOrderDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(OrderStatus.CREATED.name()))
                .andExpect(jsonPath("$.totalAmount").value(200.0));
    }

    @Test
    void shouldUpdateOrder() throws Exception {
        OrderDTO updatedOrderDTO = orderMapper.toDTO(testOrder);
        updatedOrderDTO.setTotalAmount(BigDecimal.valueOf(150.0));

        mockMvc.perform(put("/api/v1/users/" + testUser.getId() + "/orders/" + testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedOrderDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount").value(150.0));
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + testUser.getId() + "/orders/" + testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}