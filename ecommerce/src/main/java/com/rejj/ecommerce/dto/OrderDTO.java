package com.rejj.ecommerce.dto;

public class OrderDTO {

    private Integer id;
    private Integer idCart;
    private Integer idCarrier;
    private Integer idClient;
    private String status;
    private String date;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer idCart, Integer idCarrier, Integer idClient, String status, String date) {
        this.id = id;
        this.idCart = idCart;
        this.idCarrier = idCarrier;
        this.idClient = idClient;
        this.status = status;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCart() {
        return idCart;
    }

    public void setIdCart(Integer idCart) {
        this.idCart = idCart;
    }

    public Integer getIdCarrier() {
        return idCarrier;
    }

    public void setIdCarrier(Integer idCarrier) {
        this.idCarrier = idCarrier;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
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
