package com.mroueh.controller;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListItemResponse;
import com.mroueh.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WishListItemController {

    private final WishListService wishListService;

    @PostMapping("/wishlist")
    public ResponseEntity<ApiResponse> addToWishList(@CookieValue("jwt") String jwt ,@RequestBody WishListItemRequest req){
        return new ResponseEntity<>(wishListService.addItem(jwt , req), HttpStatus.CREATED);
    }
    @GetMapping("/wishlist")
    public ResponseEntity<List<CartItemResponse>> getAllItem(@CookieValue("jwt") String jwt ){
        return new ResponseEntity<>(wishListService.GetAllShoppingCartItems(jwt), HttpStatus.CREATED);
    }
}
