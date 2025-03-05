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
    @JoinColumn(name = "ID_CARRIER", nullable = false)
    private Carrier carrier;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT", nullable = false)
    private User clientO;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE", nullable = false, updatable = false)
    private Date date;

    public Order() {
    }

    public Order(Integer id, Cart cart, Carrier carrier, User clientO, String status, Date date) {
        this.id = id;
        this.cart = cart;
        this.carrier = carrier;
        this.clientO = clientO;
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

    public User getClient() {
        return clientO;
    }

    public void setClient(User clientO) {
        this.clientO = clientO;
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
