package com.rejj.ecommerce.controller;

import com.rejj.ecommerce.dto.OrderCreateRequest;
import com.rejj.ecommerce.dto.OrderDTO;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.model.OrderStatus;
import com.rejj.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @orderService.getOrderById(#id).client.username == authentication.name")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderCreateRequest request) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId_client(request.getClientId());
        orderDTO.setId_cart(request.getClientId()); // Using client ID as cart ID for now
        orderDTO.setId_carrier(request.getClientId()); // Using client ID as carrier ID for now
        orderDTO.setStatus("PENDING");
        
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasRole('ADMIN') or @clientService.getClientById(#clientId).username == authentication.name")
    public ResponseEntity<List<Order>> getOrdersByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(orderService.getOrdersByClient(clientId));
    }
} 