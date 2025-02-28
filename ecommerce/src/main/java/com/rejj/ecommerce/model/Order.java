package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import java.util.Date;

@Table(name = "ORDERS")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORDER")
    private Integer id;

    @OneToOne(mappedBy = "order")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ID_CARRIER")
    private Carrier carrier;

    @ManyToOne
    @JoinColumn(name = "ID_USER", nullable = false)
    private Client client;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE", nullable = false, updatable = false)
    private Date date;

}
