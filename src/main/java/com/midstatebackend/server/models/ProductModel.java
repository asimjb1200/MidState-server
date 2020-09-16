package com.midstatebackend.server.models;

import java.util.List;

import lombok.Data;

@Data
public class ProductModel {
    private final String productName;
    private final String productID;
    private final Integer price;
    private final String photoUrl;
    private String productSex = "Male";
}
