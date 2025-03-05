package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment_updates")
public class ShipmentUpdate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Column(nullable = false)
    private String location;
    
    @Column(nullable = false)
    private String status;
    
    @Column(length = 1000)
    private String description;
    
    public ShipmentUpdate() {
        this.timestamp = LocalDateTime.now();
    }
} 