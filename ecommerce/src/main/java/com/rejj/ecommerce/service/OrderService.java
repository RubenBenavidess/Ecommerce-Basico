package com.rejj.ecommerce.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rejj.ecommerce.dto.OrderDTO;
import com.rejj.ecommerce.model.*;
import com.rejj.ecommerce.repository.*;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CarrierRepository carrierRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CarrierRepository carrierRepository, ClientRepository clientRepository, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.carrierRepository = carrierRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    /*
     * CRUD
     */

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public OrderDTO getOrderDTOById(Long id) {
        Order order = getOrderById(id);
        return convertToDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = new Order();

        Cart cart = cartRepository.findById(orderDTO.getId_cart())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Carrier carrier = carrierRepository.findById(orderDTO.getId_carrier())
                .orElseThrow(() -> new RuntimeException("Carrier not found"));

        Client client = clientRepository.findById(orderDTO.getId_client())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        // Copy items from cart to order
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice().doubleValue());
            order.addItem(orderItem);
        }

        order.setClient(client);
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(client.getAddress());

        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        Client client = order.getClient();
        client.removeOrder(order);
        orderRepository.delete(order);
    }

    public List<Order> getOrdersByClient(Long clientId) {
        return clientService.getClientOrders(clientId);
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
            order.getId(),
            order.getClient().getId(), // Using client ID instead of cart ID since we don't store cart reference
            order.getClient().getId(), // Using client ID as carrier ID for now
            order.getClient().getId(),
            order.getStatus().toString(),
            order.getOrderDate().toString()
        );
    }
}
