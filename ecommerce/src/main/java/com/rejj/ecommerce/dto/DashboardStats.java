package com.rejj.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class DashboardStats {
    private long totalUsers;
    private long totalOrders;
    private BigDecimal totalRevenue;
    private long totalProducts;
    private List<ProductStats> topProducts;
    private Map<String, Long> ordersByStatus;
    private Map<String, BigDecimal> revenueByMonth;
}

