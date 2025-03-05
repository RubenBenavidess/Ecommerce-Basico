package com.rejj.ecommerce.model;

import jakarta.persistence.*;

@Table(name = "CARRIERS")
@Entity
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRIER")
    private Long id;

    @Column(name = "CARRIER_NAME", nullable = false)
    private String carrierName;

    public Carrier() {
    }

    public Carrier(Long id, String carrierName) {
        this.id = id;
        this.carrierName = carrierName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

}
