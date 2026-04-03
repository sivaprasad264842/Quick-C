package com.quickc.controller;

import com.quickc.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.quickc.model.Product;
import com.quickc.model.CartItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam String productId, @RequestParam int quantity) {
        // Fetch product from service, add to cart
        Product product = new Product(productId, "", 0.0, "", ""); // Dummy product fetch; implement actual fetch
        cartService.addToCart(product, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCart());
        model.addAttribute("total", cartService.getCart().stream().mapToDouble(CartItem::getTotal).sum());
        return "cart";
    }

    // Similar for /wishlist
}
