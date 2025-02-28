package com.rejj.ecommerce.model;

import java.util.Objects;

public class CartDetailID {
    private Integer product;
    private Integer cart;

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
