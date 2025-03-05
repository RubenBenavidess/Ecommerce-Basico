package com.rejj.ecommerce.dto;

public class CartDetailDTO {

    private Integer id_prod;
    private Integer id_cart;
    private Integer items;

    public CartDetailDTO() {
    }

    public CartDetailDTO(Integer id_cart, Integer id_prod, Integer items) {
        this.id_cart = id_cart;
        this.id_prod = id_prod;
        this.items = items;
    }

    public Integer getId_cart() {
        return id_cart;
    }

    public void setId_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public Integer getId_prod() {
        return id_prod;
    }

    public void setId_prod(Integer id_prod) {
        this.id_prod = id_prod;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
    

}
