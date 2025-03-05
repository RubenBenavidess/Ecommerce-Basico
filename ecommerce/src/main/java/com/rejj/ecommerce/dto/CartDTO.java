package com.rejj.ecommerce.dto;

public class CartDTO {

    private Integer id_cart;
    private Integer id_order;
    private Integer id_client;

    public CartDTO() {
    }

    public CartDTO(Integer id_cart, Integer id_order, Integer id_client) {
        this.id_cart = id_cart;
        this.id_order = id_order;
        this.id_client = id_client;
    }

    public Integer getId_cart() {
        return id_cart;
    }

    public void setId_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
        this.id_client = id_client;
    }

}
