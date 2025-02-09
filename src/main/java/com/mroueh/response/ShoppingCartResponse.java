package com.mroueh.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
public class ShoppingCartResponse {

    private List<CartItemResponse> shoppingCartItems  = new ArrayList<>();

}
