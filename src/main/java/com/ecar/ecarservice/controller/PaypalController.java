package com.ecar.ecarservice.controller;

import com.ecar.ecarservice.enitiies.PaymentTransaction;
import com.ecar.ecarservice.payload.requests.PaymentRequest;
import com.ecar.ecarservice.payload.responses.PaymentResponse;
import com.ecar.ecarservice.repositories.PaymentTransactionRepository;
import com.ecar.ecarservice.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PaypalController {

    private final PaymentService paymentService;
    private final PaymentTransactionRepository transactionRepository;

    public PaypalController(PaymentService paymentService, PaymentTransactionRepository transactionRepository) {
        this.paymentService = paymentService;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/payment/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

    // Endpoint mới: trả về tất cả transaction
    @GetMapping("/payment/transactions")
    public ResponseEntity<List<PaymentTransaction>> getAllTransactions() {
        List<PaymentTransaction> transactions = transactionRepository.findAll();
        return ResponseEntity.ok(transactions);
    }
}
