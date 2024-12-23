package com.example.eshop.tests.controller;

import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.repositories.OrderRepository;
import com.example.eshop.db.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user = userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(BigDecimal.valueOf(100.50));
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);
    }

    @Test
    void shouldGetAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].totalAmount").value(100.50))
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        String orderJson = """
                    {
                        "userId": 1,
                        "totalAmount": "200",
                        "status": "PENDING"
                    }
                """;

        mockMvc.perform(post("/api/v1/users/1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.totalAmount").value(200.00))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

}
