package com.rejj.ecommerce.controller;

import com.rejj.ecommerce.dto.CartItemRequest;
import com.rejj.ecommerce.dto.CartResponse;
import com.rejj.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@PreAuthorize("hasRole('USER')")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartResponse> getCart(@RequestAttribute Long clientId) {
        return ResponseEntity.ok(cartService.getCart(clientId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponse> addToCart(
            @RequestAttribute Long clientId,
            @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addToCart(clientId, request));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> updateCartItem(
            @RequestAttribute Long clientId,
            @PathVariable Long itemId,
            @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.updateCartItem(clientId, itemId, request));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartResponse> removeFromCart(
            @RequestAttribute Long clientId,
            @PathVariable Long itemId) {
        return ResponseEntity.ok(cartService.removeFromCart(clientId, itemId));
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(@RequestAttribute Long clientId) {
        cartService.clearCart(clientId);
        return ResponseEntity.noContent().build();
    }
} 