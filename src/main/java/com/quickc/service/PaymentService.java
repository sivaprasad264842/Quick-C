package com.quickc.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(double amount, String cardDetails) {
        // Mock payment: Always succeed in test mode
        // In real: Integrate Stripe SDK (free to use test keys)
        System.out.println("Mock payment of " + amount + " processed.");
        return true;  // Simulate success
    }
}
