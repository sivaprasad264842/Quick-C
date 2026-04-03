package com.quickc.service;

import com.quickc.model.Product;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> products = Arrays.asList(
            new Product("1", "Men Shirt", 20.0, "Men-Shirts", "/images/shirt.jpg"),
            new Product("2", "Women Dress", 30.0, "Women-Dresses", "/images/dress.jpg"),
            new Product("3", "Kids T-Shirt", 15.0, "Children-KidsWear", "/images/kids.jpg")
    // Add more...
    );

    public List<Product> getProductsByCategory(String category) {
        // Filter products by category
        return products.stream()
                .filter(p -> p.getCategory().startsWith(category))
                .collect(Collectors.toList());
    }
}
