package com.rejj.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.repository.ClientRepository;
import com.rejj.ecommerce.repository.OrderRepository;
import com.rejj.ecommerce.model.Client;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.dto.ClientDTO;
import com.rejj.ecommerce.model.Cart;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public ClientService(ClientRepository clientRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return convertToDTO(client);
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = new Client();
        updateClientFromDTO(client, clientDTO);
        
        if (clientDTO.getCarts() != null && !clientDTO.getCarts().isEmpty()) {
            List<Cart> carts = cartRepository.findAllById(clientDTO.getCarts());
            carts.forEach(client::addCart);
        }

        if (clientDTO.getOrders() != null && !clientDTO.getOrders().isEmpty()) {
            List<Order> orders = orderRepository.findAllById(clientDTO.getOrders());
            orders.forEach(client::addOrder);
        }

        Client savedClient = clientRepository.save(client);
        return convertToDTO(savedClient);
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));

        updateClientFromDTO(client, clientDTO);
        
        // Clear existing relationships
        client.getCarts().forEach(client::removeCart);
        client.getOrders().forEach(client::removeOrder);
        
        // Set new relationships
        if (clientDTO.getCarts() != null && !clientDTO.getCarts().isEmpty()) {
            List<Cart> carts = cartRepository.findAllById(clientDTO.getCarts());
            carts.forEach(client::addCart);
        }

        if (clientDTO.getOrders() != null && !clientDTO.getOrders().isEmpty()) {
            List<Order> orders = orderRepository.findAllById(clientDTO.getOrders());
            orders.forEach(client::addOrder);
        }

        Client updatedClient = clientRepository.save(client);
        return convertToDTO(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        clientRepository.delete(client);
    }

    public void blockClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        client.setBlocked(true);
        clientRepository.save(client);
    }

    public void unblockClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        client.setBlocked(false);
        clientRepository.save(client);
    }

    public List<Order> getClientOrders(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return client.getOrders();
    }

    public Client getClientEntityById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    private void updateClientFromDTO(Client client, ClientDTO dto) {
        client.setUsername(dto.getName());
        client.setAddress(dto.getAddress());
        client.setBlocked(dto.isBlocked());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
    }

    private ClientDTO convertToDTO(Client client) {
        List<Long> carts = (client.getCarts() != null) ?
                client.getCarts().stream()
                        .map(Cart::getId)
                        .collect(Collectors.toList())
                : List.of();

        List<Long> orders = (client.getOrders() != null) ?
                client.getOrders().stream()
                        .map(Order::getId)
                        .collect(Collectors.toList())
                : List.of();

        return new ClientDTO(
                client.getId(),
                client.getUsername(),
                client.getEmail(),
                client.getPassword(),
                client.getAddress(),
                client.isBlocked(),
                carts,
                orders
        );
    }
}
