package com.mruruc.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long orderItemId;
    private Long productId;
    private Long orderId;
    private Long quantity;
    private Double unitPrice;

    public OrderItem(){}
}
