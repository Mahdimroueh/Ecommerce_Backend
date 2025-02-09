package com.mroueh.service;

import com.mroueh.dto.ShoppingCartDto;
import com.mroueh.entity.ShoppingCart;
import com.mroueh.entity.ShoppingCartItem;
import com.mroueh.entity.SizeVariation;
import com.mroueh.exception.InsufficientQuantityException;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ShoppingCartResponse;

public interface ShoppingCartService {

    ApiResponse addItemToCart(String username , Long sizeVariationId , int quantity);

    ShoppingCartResponse GetAllShoppingCartItems(String jwt);

    public ApiResponse updateItemCart(String jwt, ShoppingCartDto req);

    public ApiResponse updateItemQuantity(String jwt, Long id , int quantity);

    ApiResponse deleteItem(String jwt, Long id);
}
