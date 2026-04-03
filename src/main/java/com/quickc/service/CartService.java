package com.quickc.service;

import com.quickc.model.CartItem;
import com.quickc.model.User;
import com.quickc.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    // In-memory for simplicity; sync to Firebase for persistence
    private List<CartItem> cart = new ArrayList<>();

    public void addToCart(Product product, int quantity) {
        // Check if item exists, update quantity
        for (CartItem item : cart) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Add new item if not found
        cart.add(new CartItem(product, quantity));
    }

    public List<CartItem> getCart() {
        return cart;
    }

    // Similar for wishlist (separate list)
}
