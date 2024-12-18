package com.example.eshop.tests.controller;


import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.rest.controllers.OrderController;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.model.OrderDTO;
import com.example.eshop.rest.model.enums.OrderStatusDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(OrderControllerTest.MockConfig.class)
class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void shouldGetAllOrders() throws Exception {
        // JPA Entity to simulate database object
        Order orderEntity = new Order();
        orderEntity.setId(1L);
        User user = new User();
        user.setId(101L);
        orderEntity.setUser(user);
        orderEntity.setTotalAmount(BigDecimal.valueOf(250.50));
        orderEntity.setStatus(OrderStatus.PENDING);

        // DTO to validate the API response
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setUserId(101L);
        orderDTO.setTotalAmount(BigDecimal.valueOf(250.50));
        orderDTO.setStatus(OrderStatusDTO.valueOf("PENDING"));

        // Mock service and mapper behavior
        Mockito.when(orderService.findAll()).thenReturn(Arrays.asList(orderEntity));
        Mockito.when(orderMapper.toDTO(orderEntity)).thenReturn(orderDTO);

        // Perform GET request
        mockMvc.perform(get("/api/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(101))
                .andExpect(jsonPath("$[0].totalAmount").value(250.50))
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    static class MockConfig {
        @Bean
        public OrderService orderService() {
            return Mockito.mock(OrderService.class);
        }

        @Bean
        public OrderMapper orderMapper() {
            return Mockito.mock(OrderMapper.class);
        }
    }
}