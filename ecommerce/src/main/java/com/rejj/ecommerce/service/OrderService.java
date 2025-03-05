package com.rejj.ecommerce.service;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.dto.OrderDTO;
import com.rejj.ecommerce.model.*;
import com.rejj.ecommerce.repository.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CarrierRepository carrierRepository;
    private final ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CarrierRepository carrierRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.carrierRepository = carrierRepository;
        this.clientRepository = clientRepository;
    }

    /*
     * CRUD
     */


    /*
     * Obtain an order by its id
     */
    public OrderDTO getOrderById(Integer id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden inexistente."));

        return new OrderDTO(
            order.getId(),
            order.getCart().getId(),
            order.getCarrier().getId(),
            order.getClient().getId(),
            order.getStatus(),
            order.getDate().toString()
        );

    }
    

    public OrderDTO createOrder(OrderDTO orderDTO) {

        Order order = new Order();

        Cart cart = cartRepository.findById(orderDTO.getId_cart())
                .orElseThrow(() -> new RuntimeException("Carrito inexistente."));

        Carrier carrier = carrierRepository.findById(orderDTO.getId_carrier())
                .orElseThrow(()-> new RuntimeException("Motorizado inexistente"));

        Client client = clientRepository.findById(orderDTO.getId_client())
                .orElseThrow(() -> new RuntimeException("Cliente inexistente"));

        order.setCart(cart);
        order.setCarrier(carrier);
        order.setClient(client);
        order.setStatus(orderDTO.getStatus());
        
        //Not sure how it will work xd

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        try{
            order.setDate(sdf.parse(orderDTO.getDate()));
        }catch(Exception e){
            e.printStackTrace();
        }

        orderRepository.save(order);
        return getOrderById(order.getId());

    }

}
