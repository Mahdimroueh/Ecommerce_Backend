package com.mroueh.controller;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListResponse;
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
        System.out.println("color-variation : " + req.getColorVariationId());
        return new ResponseEntity<>(wishListService.addItem(jwt , req), HttpStatus.CREATED);
    }
    @GetMapping("/wishlist")
    public ResponseEntity<List<WishListResponse>> getAllItem(@CookieValue("jwt") String jwt ){
        return new ResponseEntity<>(wishListService.GetAllShoppingCartItems(jwt), HttpStatus.OK);
    }
    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<ApiResponse> deleteItem(@CookieValue("jwt") String jwt , @RequestParam("isSize") boolean isSize , @PathVariable("id") Long id){

        if(isSize){
            return new ResponseEntity<>(wishListService.deleteItem(jwt , id , null ), HttpStatus.OK);
        }else{

            return new ResponseEntity<>(wishListService.deleteItem(jwt , null , id ), HttpStatus.OK);
        }

    }

    @PutMapping("/wishlist/{id}")
    public ResponseEntity<ApiResponse> updateItem(@CookieValue("jwt") String jwt , @RequestParam("isSize") boolean isSize , @PathVariable("id") Long id , @RequestParam("newSize") Long newSizeId){

        return new ResponseEntity<>(wishListService.updateSize(jwt , id , newSizeId , isSize), HttpStatus.OK);

    }

}
