package com.rejj.ecommerce.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.repository.ClientRepository;
import com.rejj.ecommerce.repository.OrderRepository;
import com.rejj.ecommerce.model.Client;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.dto.ClientDTO;
import com.rejj.ecommerce.model.Cart;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository, CartRepository cartRepository, OrderRepository orderRepository){
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
    
    public ClientDTO getClientById(Integer id){
        
        Client client = clientRepository.findById(id)
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

        return new ClientDTO(
            client.getId(),
            client.getName(),
            client.getEmail(),
            client.getPassword(),
            client.getAddress(),
            client.isBlocked(),
            carts,
            orders
        );

    }

    /*
      * Create a client
      */

    public ClientDTO createClient(ClientDTO clientDTO){

        Client client = new Client();

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

        clientRepository.save(client);
        return getClientById(client.getId());
    }

    /*
     * Update client
     */

    public ClientDTO updateClient(Integer id, ClientDTO clientDTO){

        Client client = clientRepository.findById(id)
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
        client.setBlocked(clientDTO.isBlocked());
        client.setEmail(clientDTO.getEmail());
        client.setPassword(clientDTO.getPassword());

        clientRepository.save(client);
        return getClientById(client.getId()); 
    }
    
    /*
     * Delete client
     */

    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }
}
