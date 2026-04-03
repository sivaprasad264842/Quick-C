package com.quickc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.quickc.model.Product;
import com.quickc.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{category}")
    public String listProducts(@PathVariable String category, Model model) {
        // Fetch products and add to model for view
        List<Product> products = productService.getProductsByCategory(category);
        model.addAttribute("products", products);
        model.addAttribute("category", category);  // For display
        return "product-list";
    }

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable String id, Model model) {
        // Find product by ID (implement findById in service)
        Product product = productService.getProductsByCategory("")  // Dummy call to get all products
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "product-detail";
    }
}
