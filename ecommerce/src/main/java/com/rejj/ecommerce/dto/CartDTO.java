package com.rejj.ecommerce.dto;

public class CartDTO {

    private Long id_cart;
    private Long id_order;
    private Long id_client;

    public CartDTO() {
    }

    public CartDTO(Long id_cart, Long id_order, Long id_client) {
        this.id_cart = id_cart;
        this.id_order = id_order;
        this.id_client = id_client;
    }

    public Long getId_cart() {
        return id_cart;
    }

    public void setId_cart(Long id_cart) {
        this.id_cart = id_cart;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

}
