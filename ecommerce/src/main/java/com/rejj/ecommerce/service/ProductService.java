package com.rejj.ecommerce.service;

import org.springframework.stereotype.Service;
import com.rejj.ecommerce.dto.ProductDTO;
import com.rejj.ecommerce.model.Product;
import com.rejj.ecommerce.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    /* CRUD */

    /* 
     * Obtain all products
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
     * Obtain a product by its id
    */
    public ProductDTO getProductById(Integer id){
        
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto inexistente."));

        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImage(),
            product.getStock(),
            product.getCategory(),
            product.getCreationDate().toString() // Not sure how it will work. I mean, probably it will charge the exact hour too
        );

    }

    /*
     * Create a product
     */

    public ProductDTO createProduct(ProductDTO productDTO){

        Product product = new Product();

        if(productDTO.getPrice() < 0){
            throw new RuntimeException("El precio no puede ser negativo.");
        }

        if(productDTO.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setStock(productDTO.getStock());
        product.setCategory(productDTO.getCategory());
    
        //Not sure how it will work xd

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        try{
            product.setCreationDate(sdf.parse(productDTO.getCreationDate()));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        productRepository.save(product);
        return getProductById(product.getId()); //It will return the product created from db

    }

    /*
     * Update a product
     */
    public ProductDTO updateProduct(Integer id, ProductDTO productDTO){

        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto inexistente."));

        if(productDTO.getPrice() < 0){
            throw new RuntimeException("El precio no puede ser negativo.");
        }

        if(productDTO.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setStock(productDTO.getStock());
        product.setCategory(productDTO.getCategory());

        //Not sure how it will work xd

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

        try{
            product.setCreationDate(sdf.parse(productDTO.getCreationDate()));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        productRepository.save(product);
        return getProductById(product.getId()); //It will return the product created from db

    }

    /*
     * Delete a product
     */
    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }   
    
    /*------------------------------------------------------- */

    /*
     * Reduce stock 
     * This method is used for a client.
     */

    public ProductDTO reduceStock(Integer id, ProductDTO productDTO){
        
        Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto inexistente."));

        if(productDTO.getStock() < 0){
            throw new RuntimeException("El stock no puede ser negativo.");
        }

        product.setStock(productDTO.getStock());

        productRepository.save(product);
        return getProductById(product.getId());
    }

}   
