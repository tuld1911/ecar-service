package com.ecar.ecarservice.payload.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String method;
    private BigDecimal amount;
    private String currency;
    private String description;

    public PaymentRequest(String method, BigDecimal amount, String currency, String description) {
        this.method = method;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }
}
