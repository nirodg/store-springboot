package com.example.eshop.tests.controller;


import com.example.eshop.db.entities.Order;
import com.example.eshop.db.services.OrderService;
import com.example.eshop.rest.controllers.OrderController;
import com.example.eshop.rest.model.OrderDTO;
import com.example.eshop.rest.model.enums.OrderStatusDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void shouldGetAllOrders() throws Exception {
        // Mock service response
        OrderDTO order = new OrderDTO();
        order.setId(1L);
        order.setUserId(101L);
        order.setTotalAmount(BigDecimal.valueOf(250.50));
        order.setStatus(OrderStatusDTO.valueOf("PENDING"));

        Mockito.when(orderService.findAll()).thenReturn(Collections.<Order>singletonList(order));

        // Perform GET request
        mockMvc.perform(get("/api/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userId").value(101))
                .andExpect(jsonPath("$[0].totalAmount").value(250.50))
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }
}