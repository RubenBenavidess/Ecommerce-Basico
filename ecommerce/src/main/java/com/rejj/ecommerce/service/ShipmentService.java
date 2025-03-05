package com.rejj.ecommerce.service;

import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.model.OrderStatus;
import com.rejj.ecommerce.model.Shipment;
import com.rejj.ecommerce.model.ShipmentUpdate;
import com.rejj.ecommerce.repository.ShipmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final OrderService orderService;

    public ShipmentService(ShipmentRepository shipmentRepository, OrderService orderService) {
        this.shipmentRepository = shipmentRepository;
        this.orderService = orderService;
    }

    public Shipment createShipment(Long orderId, String carrier, LocalDateTime estimatedDeliveryDate) {
        Order order = orderService.getOrderById(orderId);
        
        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new RuntimeException("Order must be confirmed before creating shipment");
        }

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setCarrier(carrier);
        shipment.setEstimatedDeliveryDate(estimatedDeliveryDate);
        shipment.setTrackingNumber(generateTrackingNumber());

        ShipmentUpdate initialUpdate = new ShipmentUpdate();
        initialUpdate.setShipment(shipment);
        initialUpdate.setLocation("Warehouse");
        initialUpdate.setStatus("Order Processed");
        initialUpdate.setDescription("Order has been processed and is ready for shipping");
        shipment.getUpdates().add(initialUpdate);

        order.setStatus(OrderStatus.SHIPPED);
        orderService.updateOrderStatus(orderId, OrderStatus.SHIPPED);
        
        return shipmentRepository.save(shipment);
    }

    public Shipment getShipment(Long id) {
        return shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
    }

    public Shipment addShipmentUpdate(Long id, String location, String status, String description) {
        Shipment shipment = getShipment(id);
        
        ShipmentUpdate update = new ShipmentUpdate();
        update.setShipment(shipment);
        update.setLocation(location);
        update.setStatus(status);
        update.setDescription(description);
        
        shipment.getUpdates().add(0, update);
        
        if (status.equalsIgnoreCase("Delivered")) {
            shipment.getOrder().setStatus(OrderStatus.DELIVERED);
            orderService.updateOrderStatus(shipment.getOrder().getId(), OrderStatus.DELIVERED);
        }
        
        return shipmentRepository.save(shipment);
    }

    private String generateTrackingNumber() {
        return "TN" + System.currentTimeMillis();
    }
} 