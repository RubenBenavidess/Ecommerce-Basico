package com.rejj.ecommerce.model;

import jakarta.persistence.*;


@Table(name = "CARTS")
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CART")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_ORDER")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT", nullable = false)
    private User client;

    public Cart() {
    }

    public Cart(Order order, User client) {
        this.order = order;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

}
