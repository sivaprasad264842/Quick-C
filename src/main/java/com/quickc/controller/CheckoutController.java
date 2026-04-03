package com.quickc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.quickc.model.Order;
import com.quickc.service.OrderService;
import com.quickc.service.PaymentService;

@Controller
public class CheckoutController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/checkout")
    public String checkout(double total, String cardDetails) {
        // Process payment
        if (paymentService.processPayment(total, cardDetails)) {
            Order order = new Order(cardDetails, null, null, total, null, cardDetails);  // Build from cart
            orderService.placeOrder(order);
            return "order-success";  // With 10-min delivery msg
        }
        return "cart";
    }
}
