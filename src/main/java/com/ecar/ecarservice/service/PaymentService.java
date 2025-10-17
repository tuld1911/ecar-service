package com.ecar.ecarservice.service;

import com.ecar.ecarservice.payload.requests.PaymentRequest;
import com.ecar.ecarservice.payload.responses.PaymentResponse;
import com.paypal.base.rest.PayPalRESTException;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest paymentRequest);
}
