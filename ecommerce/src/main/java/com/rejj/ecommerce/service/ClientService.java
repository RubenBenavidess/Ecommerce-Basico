package com.rejj.ecommerce.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.repository.UserRepository;
import com.rejj.ecommerce.repository.OrderRepository;
import com.rejj.ecommerce.model.User;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.dto.UserDTO;
import com.rejj.ecommerce.model.Cart;
import java.util.List;

@Service
public class ClientService {

    private final UserRepository clientRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public ClientService(UserRepository clientRepository, CartRepository cartRepository, OrderRepository orderRepository){
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    /*
     * CRUD
     */

    /*
     * Get a client by its id
     */
    
    public UserDTO getClientById(Integer id){
        
        User client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente inexistente."));

        List<Integer> carts = (client.getCarts() != null) ?
        client.getCarts().stream()
                .map(Cart::getId)
                .collect(Collectors.toList())
        : List.of();

        List<Integer> orders = (client.getOrders() != null) ?
        client.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList())
        : List.of();

        return new UserDTO(
            client.getId(),
            client.getName(),
            client.getEmail(),
            client.getPassword(),
            client.getAddress(),
            client.getRole(),
            client.getBlocked(),
            carts,
            orders
        );

    }

    /*
      * Create a client
      */

    public UserDTO createClient(UserDTO clientDTO){

        User client = new User();

        if(clientDTO.getCarts() != null & !clientDTO.getCarts().isEmpty()){
            List<Cart> carts = cartRepository.findAllById(clientDTO.getCarts());
            client.setCarts(carts);
        }

        if(clientDTO.getOrders() != null & !clientDTO.getOrders().isEmpty()){
            List<Order> orders = orderRepository.findAllById(clientDTO.getOrders());
            client.setOrders(orders);
        }

        
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setBlocked(clientDTO.isBlocked());
        client.setEmail(clientDTO.getEmail());

        client.setPassword(clientDTO.getPassword());

        

        client.setRole(clientDTO.getRole());
        client.setBlocked(clientDTO.isBlocked());

        clientRepository.save(client);
        return getClientById(client.getId());
    }

    /*
     * Update client
     */

    public UserDTO updateClient(Integer id, UserDTO clientDTO){

        User client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente inexistente."));

        if(clientDTO.getCarts() != null & !clientDTO.getCarts().isEmpty()){
            List<Cart> carts = cartRepository.findAllById(clientDTO.getCarts());
            client.setCarts(carts);
        }

        if(clientDTO.getOrders() != null & !clientDTO.getOrders().isEmpty()){
            List<Order> orders = orderRepository.findAllById(clientDTO.getOrders());
            client.setOrders(orders);
        }

        
        client.setName(clientDTO.getName());
        client.setAddress(clientDTO.getAddress());
        client.setEmail(clientDTO.getEmail());

        clientRepository.save(client);
        return getClientById(client.getId()); 
    }
    
    /*
     * Block client
     */

    public void blockClient(Integer id){
        
        User client = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente inexistente."));

        client.setBlocked(true);

        clientRepository.save(client);

    }
}
