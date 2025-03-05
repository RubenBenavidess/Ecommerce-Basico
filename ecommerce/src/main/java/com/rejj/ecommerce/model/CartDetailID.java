package com.rejj.ecommerce.model;

import java.util.Objects;

public class CartDetailID {
    
    private Integer product;
    private Integer cart;

    // Constructor vacío
    public CartDetailID() {}

    // Constructor con parámetros
    public CartDetailID(Integer product, Integer cart) {
        this.product = product;
        this.cart = cart;
    }
    // Getters y Setters
    public Integer getProduct() { return product; }
    public void setProduct(Integer product) { this.product = product; }
    public Integer getIdParte2() { return cart; }
    public void setCart(Integer cart) { this.cart = cart; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDetailID that = (CartDetailID) o;
        return Objects.equals(product, that.product) && Objects.equals(cart, that.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, cart);
    }
}
