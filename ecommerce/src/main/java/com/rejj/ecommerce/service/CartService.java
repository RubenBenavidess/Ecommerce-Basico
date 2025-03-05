package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.repository.UserRepository;
import com.rejj.ecommerce.repository.OrderRepository;
import com.rejj.ecommerce.model.Cart;
import com.rejj.ecommerce.model.User;
import com.rejj.ecommerce.model.Order;
import com.rejj.ecommerce.dto.CartDTO;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository clientRepository;
    private final OrderRepository orderRepository;

    public CartService(CartRepository cartRepository, UserRepository clientRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    /* CRUD */


    /*
     * Obtain cart by its id
     */
    public CartDTO getCartById(Integer id){

        Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Carrito no encontrado."));

        CartDTO cartDTO = new CartDTO();

        cartDTO.setId_cart(cart.getId());
        cartDTO.setId_client(cart.getClient().getId());

        if(cart.getOrder() != null){
            cartDTO.setId_order(cart.getOrder().getId());
        }

        return cartDTO;
    
    }

    /*
     * Create a Cart
     */

    public CartDTO createCart(CartDTO cartDTO){
        
        Cart cart = new Cart();

        if(cartDTO.getId_client() != null){
            User client = clientRepository.findById(cartDTO.getId_client())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado."));
            cart.setClient(client);
        }else{
            throw new RuntimeException("El ID del cliente no puede ser nulo.");
        }

        cartRepository.save(cart);
        return getCartById(cart.getId());
    }

    /*
     * Delete cart
     */
    public void deleteCart(Integer id){
        cartRepository.deleteById(id);
    }

    /*
     * Add order to cart
     */
    public CartDTO addOrderToCart(Integer idCart, Integer idOrder){

        Cart cart = cartRepository.findById(idCart)
        .orElseThrow(() -> new RuntimeException("Carrito no encontrado."));

        Order order = orderRepository.findById(idOrder)
        .orElseThrow(() -> new RuntimeException("Orden no encontrada.")); 

        cart.setOrder(order);

        cartRepository.save(cart);
        return getCartById(cart.getId());
    }

}
