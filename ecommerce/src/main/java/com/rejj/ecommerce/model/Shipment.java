package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "shipments")
public class Shipment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @Column(nullable = false)
    private String trackingNumber;
    
    @Column(nullable = false)
    private String carrier;
    
    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryDate;
    
    @Column(length = 1000)
    private String notes;
    
    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("timestamp DESC")
    private List<ShipmentUpdate> updates = new ArrayList<>();
} 