package com.mruruc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShoppingCart {
    private Long cartId;
    private Long userId;
    private Long productId;
    private Integer quantity;

    public ShoppingCart() {}

    public ShoppingCart(Long userId,Long productId){
        this.userId=userId;
        this.productId=productId;
    }
}
