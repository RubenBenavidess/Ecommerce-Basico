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

}
