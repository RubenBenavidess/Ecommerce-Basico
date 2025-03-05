package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;

import com.rejj.ecommerce.dto.CartDetailDTO;
import com.rejj.ecommerce.model.*;
import com.rejj.ecommerce.repository.*;

@Service
public class CartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    /*
     * CRUD
     */

    /*
     * Get a cart detail by their ids
     */
    public CartDetailDTO getCartDetailByIds(CartDetailID cartDetailID) {
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailID)
                .orElseThrow(() -> new RuntimeException("Cart detail not found"));

        return new CartDetailDTO(
                cartDetail.getCart().getId(),
                cartDetail.getProduct().getId(),
                cartDetail.getItems()
        );
    }
    /*
     * Create cart detail
     */
    public CartDetailDTO createCartDetail(CartDetailDTO cartDetailDTO) {
        CartDetail cartDetail = new CartDetail();

        Product product = productRepository.findById(cartDetailDTO.getId_prod())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cart = cartRepository.findById(cartDetailDTO.getId_cart())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartDetail.setProduct(product);
        cartDetail.setCart(cart);
        cartDetail.setItems(cartDetailDTO.getItems());

        cartDetailRepository.save(cartDetail);
        CartDetailID id = new CartDetailID(cartDetail.getProduct().getId(), cartDetail.getCart().getId());
        return getCartDetailByIds(id);
    }

    /*
     * Delete cart detail
     */
    public void deleteCartDetail(CartDetailID cartDetailID) {
        cartDetailRepository.deleteById(cartDetailID);
    }

    
}
