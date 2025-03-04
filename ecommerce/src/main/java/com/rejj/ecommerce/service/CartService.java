package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.repository.CartRepository;
import com.rejj.ecommerce.model.Cart;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    /* CRUD */

    /* 
     * Obtain all carts
    */


}
