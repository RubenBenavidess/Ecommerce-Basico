package com.rejj.ecommerce.model;

import jakarta.persistence.*;
import java.util.List;

@Table(name = "CLIENTS")
@Entity
public class Client extends User {
    
    @Column(name = "BLOCKED", nullable = false)
    private boolean blocked;

    @OneToMany(mappedBy = "clientC")
    private List<Cart> carts;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public Client() {
        super();
        this.blocked = false;
    }

    public Client(Integer id, String name, String email, String password, String address, boolean blocked,
                List<Cart> carts, List<Order> orders) {
        super(id, name, email, password, address);
        this.blocked = blocked;
        this.carts = carts;
        this.orders = orders;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
