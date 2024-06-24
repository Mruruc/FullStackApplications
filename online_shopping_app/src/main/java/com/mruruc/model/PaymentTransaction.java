package com.mruruc.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentTransaction {
    private Long transactionId;
    private Long orderId;
    private Double amount;
    private LocalDateTime transactionDate;
    private String paymentMethod;
    private String status;

    public PaymentTransaction() {}
}
