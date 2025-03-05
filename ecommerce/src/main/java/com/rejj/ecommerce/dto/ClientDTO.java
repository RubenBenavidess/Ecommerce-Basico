package com.rejj.ecommerce.dto;

import java.util.List;

public class ClientDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private boolean blocked;
    private List<Long> carts;
    private List<Long> orders;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email, String password, String address, boolean blocked, List<Long> carts, List<Long> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.blocked = blocked;
        this.carts = carts;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Long> getCarts() {
        return carts;
    }

    public void setCarts(List<Long> carts) {
        this.carts = carts;
    }

    public List<Long> getOrders() {
        return orders;
    }

    public void setOrders(List<Long> orders) {
        this.orders = orders;
    }


}
