package com.rejj.ecommerce.dto;

import java.util.List;

public class ClientDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String address;
    private boolean blocked;
    private List<Integer> carts;
    private List<Integer> orders;

    public ClientDTO() {
    }

    public ClientDTO(Integer id, String name, String email, String password, String address, boolean blocked, List<Integer> carts, List<Integer> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.blocked = blocked;
        this.carts = carts;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Integer> getCarts() {
        return carts;
    }

    public void setCarts(List<Integer> carts) {
        this.carts = carts;
    }

    public List<Integer> getOrders() {
        return orders;
    }

    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }


}
