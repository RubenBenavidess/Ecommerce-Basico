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
    @JoinColumn(name = "ID_USER", nullable = false)
    private Client clientC;

    public Cart() {
    }

    public Cart(Order order, Client clientC) {
        this.order = order;
        this.clientC = clientC;
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

    public Client getClientC() {
        return clientC;
    }

    public void setClientC(Client clientC) {
        this.clientC = clientC;
    }

}
