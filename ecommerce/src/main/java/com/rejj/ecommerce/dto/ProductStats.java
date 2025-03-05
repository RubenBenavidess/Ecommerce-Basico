package com.rejj.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductStats {
    private Long productId;
    private String productName;
    private long quantitySold;
    private BigDecimal revenue;
}
