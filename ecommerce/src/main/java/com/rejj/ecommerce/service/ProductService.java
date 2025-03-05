package com.rejj.ecommerce.service;

import com.rejj.ecommerce.dto.ProductRequest;
import com.rejj.ecommerce.dto.ProductResponse;
import com.rejj.ecommerce.model.Category;
import com.rejj.ecommerce.model.Product;
import com.rejj.ecommerce.repository.CategoryRepository;
import com.rejj.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findByActiveTrue(pageable)
                .map(this::mapToProductResponse);
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToProductResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        Product product = new Product();
        updateProductFromRequest(product, request, category);
        
        Product savedProduct = productRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        updateProductFromRequest(product, request, category);
        
        Product updatedProduct = productRepository.save(product);
        return mapToProductResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setActive(false);
        productRepository.save(product);
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public Page<ProductResponse> searchProducts(String query, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(query, pageable)
                .map(this::mapToProductResponse);
    }

    private void updateProductFromRequest(Product product, ProductRequest request, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);
        product.setActive(true);
    }

    private ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setImageUrl(product.getImageUrl());
        response.setCategoryName(product.getCategory().getName());
        response.setActive(product.isActive());
        return response;
    }
}   
