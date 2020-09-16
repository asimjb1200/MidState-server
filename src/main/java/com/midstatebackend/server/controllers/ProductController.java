package com.midstatebackend.server.controllers;

import java.util.ArrayList;
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
import com.midstatebackend.server.models.ProductModel;

@RestController
@CrossOrigin
@Data
public class ProductController {
    // retrieve key from properties file
    @Value("${stripe.test_key}")
    private String testKey;

    @GetMapping("/")
    public String home() {

        return "Hello from the server";
    }

    @GetMapping("/all-products")
    public String allProducts() throws StripeException {
        Stripe.apiKey = testKey;
        var products = new ArrayList<ProductModel>();
        Gson gson = new Gson();

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);

        // retrieve the products from stripe's api
        ProductCollection stripeProducts = Product.list(params);

        // loop through the products and pull out the data I need
        for (Product x : stripeProducts.getData()) {
            products.add(new ProductModel(x.getName(), x.getId(), 20, x.getImages().get(0)));
        }

        String productDataStringified = gson.toJson(products);

        return productDataStringified;
    }
}
