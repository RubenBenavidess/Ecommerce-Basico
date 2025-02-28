package com.rejj.ecommerce.model;

import jakarta.persistence.*;

@Table(name = "CARTS_DETAILS")
@Entity
@IdClass(CartDetailID.class)
public class CartDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "ID_PROD", nullable = false)
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_CART", nullable = false)
    private Cart cart;

    @Column(name = "ITEMS", nullable = false)
    private Integer items;

    public CartDetail() {
    }

    public CartDetail(Product product, Cart cart, Integer items) {
        this.product = product;
        this.cart = cart;
        this.items = items;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
}
