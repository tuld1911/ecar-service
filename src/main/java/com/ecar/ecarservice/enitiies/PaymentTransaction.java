package com.ecar.ecarservice.enitiies;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_transaction")
@Getter
@Setter
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paypalPaymentId;

    private String currency;

    private BigDecimal amount;

    private String status;

    private String description;

    private LocalDateTime createdAt;
}

