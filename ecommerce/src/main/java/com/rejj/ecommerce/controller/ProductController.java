package com.rejj.ecommerce.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.rejj.ecommerce.service.ProductService;
import com.rejj.ecommerce.dto.ProductDTO;
import java.util.List;


@RestController
@RequestMapping("/api/ecommerce/products")
public class ProductController {

    private final ProductService productService; 

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /*
     * Get product by its id
     */

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /*
     * Create product
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {        
        ProductDTO newProduct = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    /*
     * Update product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateClient(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {        
        ProductDTO product = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(product);
    }

    /*
     * Reduce stock
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> reduceStock(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {        
        ProductDTO product = productService.reduceStock(id, productDTO);
        return ResponseEntity.ok(product);
    }

    /*
     * Delete product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
