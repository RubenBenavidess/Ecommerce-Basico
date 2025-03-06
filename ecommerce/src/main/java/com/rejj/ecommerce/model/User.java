package com.rejj.ecommerce.model;

import java.util.List;

import jakarta.persistence.*;

@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "ROLE", nullable = false)
    private String role;

    @Column(name = "BLOCKED", nullable = false)
    private boolean blocked;

    @OneToMany(mappedBy = "client")
    private List<Cart> carts;

    @OneToMany(mappedBy = "clientO")
    private List<Order> orders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Token> tokens;

    public User() {
    }

    public User(Integer id, String name, String email, String password, String address,
                String role, boolean blocked, List<Cart> carts, List<Order> orders,
                List<Token> tokens) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.blocked = blocked;
        this.carts = carts;
        this.orders = orders;
        this.tokens = tokens;
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

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    public void setBlocked(boolean blocked){
        this.blocked = blocked;
    }

    public boolean getBlocked(){
        return blocked;
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

    public void setTokens(List<Token> tokens){
        this.tokens = tokens;
    }

    public List<Token> getTokens(){
        return tokens;
    }

}
