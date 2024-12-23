package com.example.eshop.rest.controllers;

import com.example.eshop.db.entities.Category;
import com.example.eshop.db.entities.Order;
import com.example.eshop.db.entities.Product;
import com.example.eshop.db.entities.User;
import com.example.eshop.db.entities.enums.OrderStatus;
import com.example.eshop.db.services.*;
import com.example.eshop.rest.mappers.CategoryMapper;
import com.example.eshop.rest.mappers.OrderMapper;
import com.example.eshop.rest.mappers.ProductMapper;
import com.example.eshop.rest.mappers.UserMapper;
import com.example.eshop.rest.model.OrderDTO;
import com.example.eshop.rest.model.ProductDTO;
import com.example.eshop.rest.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * API v1 Controller for managing user-related operations.
 * This controller provides endpoints for:
 * - Managing users
 * - Managing orders for specific users
 * - Enforcing proper business rules
 */
@RestController
@RequestMapping("/api/v1")
public class ApiV1Controller {

    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final CartService cartService;

    public ApiV1Controller(UserService userService, UserMapper userMapper, OrderService orderService, OrderMapper orderMapper,
                           ProductService productService, ProductMapper productMapper,
                           CategoryService categoryService, CategoryMapper categoryMapper,
                           CartService cartService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.cartService = cartService;
    }

    /**
     * Creates a new product.
     *
     * @param productDTO the product data to create
     * @return the created ProductDTO
     */
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createNewProduct(@RequestBody ProductDTO productDTO) {
        // Convert DTO to entity
        Product product = productMapper.toEntity(productDTO);
        product.setCategory(
                productDTO.getCategoryId() != null
                        ? categoryService.findById(productDTO.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Category not found"))
                        : null
        );
        Product savedProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDTO(savedProduct));
    }

    /**
     * Retrieves all products regardless of their category.
     *
     * @return a list of all ProductDTOs
     */
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productMapper.toDTOs(productService.findAll()));
    }


    /**
     * Retrieves all products for a specific category.
     *
     * @param categoryId the ID of the category
     * @return a list of ProductDTOs within the specified category
     */
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return ResponseEntity.ok(productMapper.toDTOs(productService.findAllByCategory(category)));
    }

    /**
     * Retrieves a specific product by its ID.
     *
     * @param productId the ID of the product
     * @return the ProductDTO of the specified product
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return ResponseEntity.ok(productMapper.toDTO(product));
    }

    /**
     * Deleting a specific product by its ID.
     *
     * @param id the ID of the product
     * @return a ResponseEntity indicating the operation result
     */
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing product.
     *
     * @param id         the ID of the product to update
     * @param productDTO the updated product data
     * @return the updated ProductDTO
     */
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productMapper.updateEntity(productDTO, existingProduct.get());
        // Map the updated data to the existing product
        existingProduct.get().setCategory(
                productDTO.getCategoryId() != null
                        ? categoryService.findById(productDTO.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("Category not found"))
                        : null
        );

        Product updatedProduct = productService.save(existingProduct.get());
        return ResponseEntity.ok(productMapper.toDTO(updatedProduct));
    }

    /**
     * Retrieves user information by user ID.
     *
     * @param userId the ID of the user
     * @return the UserDTO representation of the user
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(userMapper.toDTO(user)))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Retrieves all orders associated with a specific user.
     *
     * @param userId the ID of the user
     * @return a list of OrderDTOs
     */
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable Long userId) {
        return userService.findById(userId)
                .map(user -> ResponseEntity.ok(orderMapper.toDTOs(orderService.findAllByUser(user))))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Retrieves a specific order for a user by order ID.
     *
     * @param userId  the ID of the user
     * @param orderId the ID of the order
     * @return the OrderDTO representation of the order
     */
    @GetMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        return userService.findById(userId)
                .flatMap(user -> orderService.findByIdAndUser(orderId, user))
                .map(order -> ResponseEntity.ok(orderMapper.toDTO(order)))
                .orElseThrow(() -> new IllegalArgumentException("Order not found for this user"));
    }

    /**
     * Creates a new order for a specific user.
     *
     * @param userId   the ID of the user
     * @param orderDTO the OrderDTO representation of the new order
     * @return the created OrderDTO
     */
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<OrderDTO> createNewOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(user.get());
        return ResponseEntity.status(201).body(orderMapper.toDTO(orderService.create(order)));
    }

    /**
     * Updates an existing order for a user.
     *
     * @param userId   the ID of the user
     * @param orderId  the ID of the order
     * @param orderDTO the updated OrderDTO
     * @return the updated OrderDTO
     */
    @PutMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long userId, @PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Order order = orderMapper.toEntity(orderDTO);
        order.setId(orderId);
        order.setUser(user.get());
        return ResponseEntity.ok(orderMapper.toDTO(orderService.update(orderId, order)));
    }

    /**
     * Cancels an order for a user. Only orders with statuses CREATED or PENDING can be canceled.
     *
     * @param userId  the ID of the user
     * @param orderId the ID of the order
     * @return a ResponseEntity indicating the operation result
     */
    @DeleteMapping("/users/{userId}/orders/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long userId, @PathVariable Long orderId) {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("Order does not belong to this user");
        }

        if (order.getStatus() != OrderStatus.CREATED && order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Order cannot be canceled as it is already being processed or fulfilled");
        }

        orderService.deleteById(orderId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates user information.
     *
     * @param userId  the ID of the user
     * @param userDTO the updated UserDTO
     * @return the updated UserDTO
     */
    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userMapper.updateEntity(userDTO, user);
        return ResponseEntity.ok(userMapper.toDTO(userService.update(userId, user)));
    }

    /**
     * Deletes a user and all associated information.
     *
     * @param userId the ID of the user
     * @return a ResponseEntity indicating the operation result
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        if (!userService.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
