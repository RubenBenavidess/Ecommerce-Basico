package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User {
    
    @Column(nullable = false)
    private String address;
    
    private boolean blocked;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    public Client() {
        super();
        this.setRole(Role.CLIENT);
        this.blocked = false;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setClient(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setClient(null);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
        cart.setClient(this);
    }

    public void removeCart(Cart cart) {
        carts.remove(cart);
        cart.setClient(null);
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}
