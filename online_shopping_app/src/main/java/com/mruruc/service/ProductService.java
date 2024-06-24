package com.mruruc.service;

import com.mruruc.exception.ProductNotFoundException;
import com.mruruc.model.Product;
import com.mruruc.repositoryImpl.ProductRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepositoryImpl productRepository;
    @Autowired
    public ProductService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    public Long addProduct(Product product) {
        return productRepository.save(product).getProductId();
    }

    public Product updateProduct(Long productId,Product product) {
        return productRepository.update(productId,product);
    }

    public Long deleteProduct(Long id) {
        return productRepository.delete(id);
    }

}
