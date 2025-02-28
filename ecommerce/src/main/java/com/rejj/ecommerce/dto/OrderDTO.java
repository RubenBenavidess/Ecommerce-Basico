package com.rejj.ecommerce.dto;

public class OrderDTO {

    private Integer id;
    private Integer id_cart;
    private Integer id_carrier;
    private Integer id_client;
    private String status;
    private String date;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer id_cart, Integer id_carrier, Integer id_client, String status, String date) {
        this.id = id;
        this.id_cart = id_cart;
        this.id_carrier = id_carrier;
        this.id_client = id_client;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_cart() {
        return id_cart;
    }

    public void setId_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public Integer getId_carrier() {
        return id_carrier;
    }

    public void setId_carrier(Integer id_carrier) {
        this.id_carrier = id_carrier;
    }

    public Integer getId_client() {
        return id_client;
    }

    public void setId_client(Integer id_client) {
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
