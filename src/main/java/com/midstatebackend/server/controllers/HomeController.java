package com.midstatebackend.server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;

import com.google.gson.Gson;

@RestController
@CrossOrigin
@Data
public class HomeController {
    @Value("${stripe.test_key}")
    private String testKey;

    @GetMapping("/")
    public String home() {

        return "Hello from the server";
    }

    @GetMapping("/all-products")
    public ProductCollection allProducts() throws StripeException {
        Stripe.apiKey = testKey;

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        ProductCollection products = Product.list(params);
        return products;
    }
}
