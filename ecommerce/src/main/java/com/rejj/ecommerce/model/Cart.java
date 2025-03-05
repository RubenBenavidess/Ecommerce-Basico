package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();
    
    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
        calculateTotal();
    }
    
    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
        calculateTotal();
    }
    
    public void updateItemQuantity(Long productId, int quantity) {
        items.stream()
            .filter(item -> item.getProduct().getId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                calculateTotal();
            });
    }
    
    public void clear() {
        items.clear();
        totalAmount = BigDecimal.ZERO;
    }
    
    private void calculateTotal() {
        totalAmount = items.stream()
            .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
