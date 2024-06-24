package com.mruruc.controller;

import com.mruruc.model.Order;
import com.mruruc.model.OrderStatus;
import com.mruruc.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String getOrderTrackingPage(){
        return "orders/order_tracking";
    }

    @PostMapping
    public String postOrderTracking(@RequestParam("orderId") Long orderId, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("products",List.of(
                new Product(21L,"Product 1","Product One Description",99.99,50,1L),
                new Product(21L,"Product 2","Product Two Description",999.99,50,1L),
                new Product(21L,"Product 3","Product Three Description",999.99,50,1L)
        ));
        redirectAttributes.addFlashAttribute("order",new Order(54505701L,12L, LocalDate.of(2030,1,25),55509.06, OrderStatus.PROCESSING, List.of()));
        return "redirect:/orders";
    }
}
