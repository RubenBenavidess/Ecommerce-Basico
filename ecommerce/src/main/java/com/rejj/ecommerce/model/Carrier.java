package com.rejj.ecommerce.model;

import jakarta.persistence.*;

@Table(name = "CARRIERS")
@Entity
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRIER")
    private Integer id;

    @Column(name = "CARRIER_NAME", nullable = false)
    private String carrierName;

    public Carrier() {
    }

    public Carrier(Integer id, String carrierName) {
        this.id = id;
        this.carrierName = carrierName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

}
