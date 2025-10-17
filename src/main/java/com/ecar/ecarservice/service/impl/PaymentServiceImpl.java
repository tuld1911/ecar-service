package com.ecar.ecarservice.service.impl;

import com.ecar.ecarservice.enitiies.PaymentTransaction;
import com.ecar.ecarservice.payload.requests.PaymentRequest;
import com.ecar.ecarservice.payload.responses.PaymentResponse;
import com.ecar.ecarservice.repositories.PaymentTransactionRepository;
import com.ecar.ecarservice.service.PaymentService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final APIContext apiContext;
    private final PaymentTransactionRepository transactionRepository;

    @Value("${paypal.successUrl}")
    private String successUrl;

    @Value("${paypal.cancelUrl}")
    private String cancelUrl;

    public PaymentServiceImpl(APIContext apiContext, PaymentTransactionRepository transactionRepository) {
        this.apiContext = apiContext;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        try {
            Amount amount = new Amount();
            amount.setCurrency(paymentRequest.getCurrency());
            amount.setTotal(String.format(Locale.forLanguageTag(paymentRequest.getCurrency()), "%.2f", paymentRequest.getAmount()));

            Transaction transaction = new Transaction();
            transaction.setDescription(paymentRequest.getDescription());
            transaction.setAmount(amount);

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payer payer = new Payer();
            payer.setPaymentMethod(paymentRequest.getMethod());

            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl(cancelUrl);
            redirectUrls.setReturnUrl(successUrl);
            payment.setRedirectUrls(redirectUrls);

            Payment rs = payment.create(apiContext);

            // Lấy link redirect
            String redirectUrl = rs.getLinks().stream()
                    .filter(l -> l.getRel().equalsIgnoreCase("approval_url"))
                    .findFirst()
                    .map(Links::getHref)
                    .orElse(null);

            // Lưu vào database
            PaymentTransaction paymentTransaction = new PaymentTransaction();
            paymentTransaction.setPaypalPaymentId(rs.getId());
            paymentTransaction.setCurrency(paymentRequest.getCurrency());
            paymentTransaction.setAmount(paymentRequest.getAmount());
            paymentTransaction.setStatus(rs.getState());
            paymentTransaction.setDescription(paymentRequest.getDescription());
            paymentTransaction.setCreatedAt(LocalDateTime.now());
            transactionRepository.save(paymentTransaction);

            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setPaymentUrl(redirectUrl);
            return paymentResponse;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return new PaymentResponse();
        }
    }
}
