package com.quickc.model;

import java.time.LocalDateTime;
import java.util.List;


public class Order {
    private String id;
    private User user;
    private List<CartItem> items;
    private double total;
    private LocalDateTime orderTime;
    private String status; // e.g., "Placed", "Delivered in 10 mins"
    
    


    public Order(String id, User user, List<CartItem> items, double total, LocalDateTime orderTime, String status) {
        this.id = id;
        this.user = user;
        this.items = items;
        this.total = total;
        this.orderTime = orderTime;
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<CartItem> getItems() {
        return items;
    }
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public LocalDateTime getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // ... getters/setters
}