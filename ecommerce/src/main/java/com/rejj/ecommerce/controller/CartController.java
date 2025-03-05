package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.CartService;
import com.rejj.ecommerce.dto.CartDTO;

@RestController
@RequestMapping("/api/ecommerce/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody CartDTO cartDTO) {        
        CartDTO newCart = cartService.createCart(cartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }

    @PutMapping("/{idCart}/{idProd}")
    public ResponseEntity<CartDTO> addOrderToCart(@PathVariable Integer idCart, @PathVariable Integer idProd) {        
        CartDTO cart = cartService.addOrderToCart(idCart, idProd);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {        
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}
