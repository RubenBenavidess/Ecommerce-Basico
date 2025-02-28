package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;
import com.rejj.ecommerce.dto.ProductDTO;
import com.rejj.ecommerce.model.Product;
import com.rejj.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /* 
     * Obtener todos los productos
    */
    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream()
        .map(product -> new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImage(),
            product.getStock(),
            product.getCategory(),
            product.getCreationDate().toString()
        )).collect(Collectors.toList());
    }

    /* 
     * Obtener un producto por su id
    */
    public ProductDTO getProductById(Integer id){
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto inexistente."));

        return null;
    }

}
