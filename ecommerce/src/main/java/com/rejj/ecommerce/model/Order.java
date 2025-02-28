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

    public Order() {
    }

    public Order(Integer id, Cart cart, Carrier carrier, Client client, String status, Date date) {
        this.id = id;
        this.cart = cart;
        this.carrier = carrier;
        this.client = client;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
