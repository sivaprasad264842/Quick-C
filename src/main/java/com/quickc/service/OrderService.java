package com.quickc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  // Similar to UserRepo

import com.quickc.model.Order;
import com.quickc.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public void placeOrder(Order order) {
        // Set status with 10-min delivery note
        order.setStatus("Placed - Delivering in 10 mins");
        orderRepo.save(order);  // To Firebase
    }
}
