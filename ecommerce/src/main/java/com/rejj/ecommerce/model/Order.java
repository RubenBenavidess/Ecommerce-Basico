package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    
    @Column(nullable = false)
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    
    private double totalAmount;
    
    @Column(length = 1000)
    private String shippingAddress;
    
    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }
    
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        calculateTotal();
    }
    
    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
        calculateTotal();
    }
    
    private void calculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
