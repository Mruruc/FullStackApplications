package com.bidAndWin.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private Long Id;
    private String method;
    private Double amount;
    private String currency;
    private String description;
    private String successUrl;
    private String cancelUrl;

    public PaymentDto() {}
}
