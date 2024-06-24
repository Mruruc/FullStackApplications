package com.mruruc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Order {
    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private double totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItem> orderItems;


    private Order(){}
}

