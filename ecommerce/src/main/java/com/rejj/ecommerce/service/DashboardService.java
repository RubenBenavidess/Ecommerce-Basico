package com.rejj.ecommerce.service;

import com.rejj.ecommerce.dto.DashboardStats;
import com.rejj.ecommerce.dto.ProductStats;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.model.OrderItem;
import com.rejj.ecommerce.model.OrderStatus;
import com.rejj.ecommerce.repository.OrderRepository;
import com.rejj.ecommerce.repository.ProductRepository;
import com.rejj.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public DashboardService(OrderRepository orderRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public DashboardStats getDashboardStats() {
        DashboardStats stats = new DashboardStats();
        
        // Basic counts
        stats.setTotalUsers(userRepository.count());
        stats.setTotalProducts(productRepository.count());
        stats.setTotalOrders(orderRepository.count());
        
        List<Order> orders = orderRepository.findAll();
        
        // Calculate total revenue
        stats.setTotalRevenue(calculateTotalRevenue(orders));
        
        // Orders by status
        stats.setOrdersByStatus(getOrdersByStatus(orders));
        
        // Revenue by month
        stats.setRevenueByMonth(getRevenueByMonth(orders));
        
        // Top products
        stats.setTopProducts(getTopProducts(orders));
        
        return stats;
    }

    private BigDecimal calculateTotalRevenue(List<Order> orders) {
        return orders.stream()
                .map(order -> BigDecimal.valueOf(order.getTotalAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<String, Long> getOrdersByStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                    order -> order.getStatus().name(),
                    Collectors.counting()
                ));
    }

    private Map<String, BigDecimal> getRevenueByMonth(List<Order> orders) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return orders.stream()
                .collect(Collectors.groupingBy(
                    order -> order.getOrderDate().format(formatter),
                    Collectors.mapping(
                        order -> BigDecimal.valueOf(order.getTotalAmount()),
                        Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                    )
                ));
    }

    private List<ProductStats> getTopProducts(List<Order> orders) {
        Map<Long, ProductStats> productStatsMap = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                    item -> item.getProduct().getId(),
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        items -> {
                            ProductStats stats = new ProductStats();
                            OrderItem firstItem = items.get(0);
                            stats.setProductId(firstItem.getProduct().getId());
                            stats.setProductName(firstItem.getProduct().getName());
                            stats.setQuantitySold(items.stream()
                                    .mapToLong(OrderItem::getQuantity)
                                    .sum());
                            stats.setRevenue(items.stream()
                                    .map(item -> BigDecimal.valueOf(item.getSubtotal()))
                                    .reduce(BigDecimal.ZERO, BigDecimal::add));
                            return stats;
                        }
                    )
                ));

        return productStatsMap.values().stream()
                .sorted((a, b) -> b.getRevenue().compareTo(a.getRevenue()))
                .limit(10)
                .collect(Collectors.toList());
    }
} 