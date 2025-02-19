package com.mroueh.service;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListResponse;

import java.util.List;

public interface WishListService {

    ApiResponse addItem(String jwt , WishListItemRequest req);

    public List<WishListResponse> GetAllShoppingCartItems(String jwt);

    public ApiResponse deleteItem(String jwt , Long sizeId , Long colorId);

    public ApiResponse updateSize(String jwt , Long sizeId , Long newSizeId , boolean isSize);
}
