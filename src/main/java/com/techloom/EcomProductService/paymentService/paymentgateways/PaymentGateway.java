package com.techloom.EcomProductService.paymentService.paymentgateways;

public interface PaymentGateway {
    String generatePaymentLink(Long orderId, Long amount, String phoneNumber) throws RazorpayException, StripeException;
}
