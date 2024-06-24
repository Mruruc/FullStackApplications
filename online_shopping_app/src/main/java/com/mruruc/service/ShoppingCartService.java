package com.mruruc.service;

import com.mruruc.model.Product;
import com.mruruc.model.ShoppingCart;
import com.mruruc.repositoryImpl.ShoppingCartRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService  {
    private ShoppingCartRepositoryImpl repository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepositoryImpl repository) {
        this.repository = repository;
    }

    public Long addProductToCart(ShoppingCart shoppingCart) {
        return repository.save(shoppingCart);
    }

    public List<Product> getAllProductInSoppingCart(Long userId){
        return repository.getAllProductInShoppingCartByUserId(userId);
    }
}
