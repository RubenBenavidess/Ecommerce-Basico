package com.rejj.ecommerce.service;

import com.rejj.ecommerce.dto.CartItemRequest;
import com.rejj.ecommerce.dto.CartItemResponse;
import com.rejj.ecommerce.dto.CartResponse;
import com.rejj.ecommerce.model.Cart;
import com.rejj.ecommerce.model.CartItem;
import com.rejj.ecommerce.model.Client;
import com.rejj.ecommerce.model.Product;
import com.rejj.ecommerce.repository.CartItemRepository;
import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ClientService clientService;

    public CartService(CartRepository cartRepository,
                      CartItemRepository cartItemRepository,
                      ProductRepository productRepository,
                      ClientService clientService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.clientService = clientService;
    }

    public CartResponse getCart(Long clientId) {
        Cart cart = cartRepository.findByClientId(clientId)
                .orElseGet(() -> createNewCart(clientId));
        return mapToCartResponse(cart);
    }

    public CartResponse addToCart(Long clientId, CartItemRequest request) {
        Cart cart = cartRepository.findByClientId(clientId)
                .orElseGet(() -> createNewCart(clientId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.isActive()) {
            throw new RuntimeException("Product is not available");
        }

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setCart(cart);
                    newItem.setProduct(product);
                    return newItem;
                });

        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        
        return mapToCartResponse(cart);
    }

    public CartResponse updateCartItem(Long clientId, Long itemId, CartItemRequest request) {
        Cart cart = getCartOrThrow(clientId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to this cart");
        }

        if (request.getQuantity() <= 0) {
            cart.removeItem(item);
            cartItemRepository.delete(item);
        } else {
            if (item.getProduct().getStock() < request.getQuantity()) {
                throw new RuntimeException("Not enough stock available");
            }
            item.setQuantity(request.getQuantity());
            cartItemRepository.save(item);
        }

        return mapToCartResponse(cart);
    }

    public CartResponse removeFromCart(Long clientId, Long itemId) {
        Cart cart = getCartOrThrow(clientId);
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("Cart item does not belong to this cart");
        }

        cart.removeItem(item);
        cartItemRepository.delete(item);
        
        return mapToCartResponse(cart);
    }

    public void clearCart(Long clientId) {
        Cart cart = getCartOrThrow(clientId);
        cart.getItems().forEach(cartItemRepository::delete);
        cart.clear();
        cartRepository.save(cart);
    }

    private Cart createNewCart(Long clientId) {
        Client client = clientService.getClientEntityById(clientId);
        Cart cart = new Cart();
        cart.setClient(client);
        return cartRepository.save(cart);
    }

    private Cart getCartOrThrow(Long clientId) {
        return cartRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Cart not found for client"));
    }

    private CartResponse mapToCartResponse(Cart cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setTotalAmount(cart.getTotalAmount());
        response.setItems(cart.getItems().stream()
                .map(this::mapToCartItemResponse)
                .collect(Collectors.toList()));
        return response;
    }

    private CartItemResponse mapToCartItemResponse(CartItem item) {
        CartItemResponse response = new CartItemResponse();
        response.setId(item.getId());
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setProductImageUrl(item.getProduct().getImageUrl());
        response.setUnitPrice(item.getProduct().getPrice());
        response.setQuantity(item.getQuantity());
        response.setSubtotal(item.getSubtotal());
        return response;
    }
}
