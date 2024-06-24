package com.mruruc.controller;

import com.mruruc.model.ShoppingCart;
import com.mruruc.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }
    
    @GetMapping
    public String viewCart(@RequestParam("userId") Long userId, Model model) {
        model.addAttribute("cartItems",shoppingCartService.getAllProductInSoppingCart(userId));
        model.addAttribute("totalAmount", 200.99);
        return "shopping-cart/shopping_cart";
    }

    @PostMapping
    public String addProductShoppingCart(@ModelAttribute ShoppingCart shoppingCart) {
        return "redirect:/shopping-cart?userId="+shoppingCartService.addProductToCart(shoppingCart);
    }


}
