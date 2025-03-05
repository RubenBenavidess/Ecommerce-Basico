package com.rejj.ecommerce.dto;

public class CartDetailDTO {

    private Long id_prod;
    private Long id_cart;
    private Integer items;

    public CartDetailDTO() {
    }

    public CartDetailDTO(Long id_cart, Long id_prod, Integer items) {
        this.id_cart = id_cart;
        this.id_prod = id_prod;
        this.items = items;
    }

    public Long getId_cart() {
        return id_cart;
    }

    public void setId_cart(Long id_cart) {
        this.id_cart = id_cart;
    }

    public Long getId_prod() {
        return id_prod;
    }

    public void setId_prod(Long id_prod) {
        this.id_prod = id_prod;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }
    

}
