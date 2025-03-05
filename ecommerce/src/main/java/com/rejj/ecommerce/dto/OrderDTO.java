package com.rejj.ecommerce.dto;

public class OrderDTO {

    private Long id;
    private Long id_cart;
    private Long id_carrier;
    private Long id_client;
    private String status;
    private String date;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Long id_cart, Long id_carrier, Long id_client, String status, String date) {
        this.id = id;
        this.id_cart = id_cart;
        this.id_carrier = id_carrier;
        this.id_client = id_client;
        this.status = status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_cart() {
        return id_cart;
    }

    public void setId_cart(Long id_cart) {
        this.id_cart = id_cart;
    }

    public Long getId_carrier() {
        return id_carrier;
    }

    public void setId_carrier(Long id_carrier) {
        this.id_carrier = id_carrier;
    }

    public Long getId_client() {
        return id_client;
    }

    public void setId_client(Long id_client) {
        this.id_client = id_client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
