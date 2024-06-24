package com.mruruc.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Long userId;
}
