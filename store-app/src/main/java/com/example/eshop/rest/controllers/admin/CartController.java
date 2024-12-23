package com.example.eshop.rest.controllers.admin;

import com.example.eshop.common.AbstractMapper;
import com.example.eshop.common.AbstractService;
import com.example.eshop.db.entities.Cart;
import com.example.eshop.db.entities.CartItem;
import com.example.eshop.db.services.CartService;
import com.example.eshop.common.AbstractController;
import com.example.eshop.rest.mappers.CartMapper;
import com.example.eshop.rest.model.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/carts")
public class CartController extends AbstractController<Cart, CartDTO, Long> {

    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    // Get the cart by user ID
    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        return cartService.findByUserId(userId).map(cartMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Add an item to the cart
    @PostMapping("/{userId}/items")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long userId, @RequestBody CartItem cartItem) {
        Cart updatedCart = cartService.addItemToCart(userId, cartItem);
        return ResponseEntity.ok(cartMapper.toDTO(updatedCart));
    }

    // Remove an item from the cart
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.removeItemFromCart(userId, itemId);
        Optional<Cart> updatedCart = cartService.findByUserId(userId);
        return updatedCart.map(cartMapper::toDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Checkout the cart and place the order
    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkoutCart(@PathVariable Long userId) {
        cartService.checkout(userId);
        return ResponseEntity.ok("Cart checked out successfully and order placed.");
    }

    @Override
    protected AbstractService<Cart, Long> service() {
        return cartService;
    }

    @Override
    protected AbstractMapper<Cart, CartDTO> mapper() {
        return cartMapper;
    }
}