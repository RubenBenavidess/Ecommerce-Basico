package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.CartDetailService;
import com.rejj.ecommerce.dto.CartDetailDTO;
import com.rejj.ecommerce.model.CartDetailID;

@RestController
@RequestMapping("/api/ecommerce/cart-detail")
public class CartDetailController {

    private final CartDetailService cartDetailService; 

    public CartDetailController(CartDetailService cartDetailService){
        this.cartDetailService = cartDetailService;
    }

    /*
     * get a cart detail
     */

    @GetMapping("/{id_prod}/{id_cart}")
    public ResponseEntity<CartDetailDTO> getCartDetail(
            @PathVariable Integer id_prod, 
            @PathVariable Integer id_cart) {
        System.out.println("-------------WORKING!!");
        CartDetailID id = new CartDetailID(id_prod, id_cart); // Crear la clave compuesta
        CartDetailDTO product = cartDetailService.getCartDetailByIds(id);

        return ResponseEntity.ok(product);
    }

    /*
    * Create cart detail
    */

    @PostMapping
    public ResponseEntity<CartDetailDTO> createCartDetail(@RequestBody CartDetailDTO cartDetailDTO) {        
        CartDetailDTO newCartDetail = cartDetailService.createCartDetail(cartDetailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCartDetail);
    }

    /*
     * Delete cart detail
     */
    @DeleteMapping("/{id_prod}/{id_cart}")
    public ResponseEntity<Void> deleteCartDetail(
        @PathVariable Integer id_prod, 
        @PathVariable Integer id_cart
    ) {
        CartDetailID id = new CartDetailID(id_prod, id_cart);
        cartDetailService.deleteCartDetail(id);
        return ResponseEntity.noContent().build();
    }

}
