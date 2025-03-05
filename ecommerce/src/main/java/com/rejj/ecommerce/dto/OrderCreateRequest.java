package com.rejj.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateRequest {
    private Long clientId;
    private String shippingAddress;
    private List<OrderItemRequest> items;
}

