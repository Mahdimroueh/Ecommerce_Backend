package com.mroueh.response;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartItemResponse {

    private Long productId;

    private String productName;

    private int quantity;

    private double unitPrice;

    private String image;

    private String color;

    private List<AvailableSize> sizes = new ArrayList<>();
}
