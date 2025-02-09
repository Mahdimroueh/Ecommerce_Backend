package com.mroueh.service;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.entity.ColorVariation;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListItemResponse;

import java.util.List;

public interface WishListService {

    ApiResponse addItem(String jwt , WishListItemRequest req);

    public List<CartItemResponse> GetAllShoppingCartItems(String jwt);
}
