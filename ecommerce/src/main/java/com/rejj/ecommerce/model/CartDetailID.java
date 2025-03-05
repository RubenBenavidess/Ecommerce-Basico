package com.rejj.ecommerce.model;

import java.util.Objects;

public class CartDetailID {
    
    private Long product;
    private Long cart;

    // Constructor vacío
    public CartDetailID() {}

    // Constructor con parámetros
    public CartDetailID(Long product, Long cart) {
        this.product = product;
        this.cart = cart;
    }
    // Getters y Setters
    public Long getProduct() { return product; }
    public void setProduct(Long product) { this.product = product; }
    public Long getCart() { return cart; }
    public void setCart(Long cart) { this.cart = cart; }

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
