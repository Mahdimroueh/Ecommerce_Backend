package com.mroueh.controller;


import com.mroueh.dto.ShoppingCartDto;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ShoppingCartResponse;
import com.mroueh.service.ShoppingCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class ShoppingCartItemController {

    private final ShoppingCartService shoppingCartService;



    @PostMapping("/cart")
    public ResponseEntity<ApiResponse> addCartItem(@CookieValue("jwt") String jwt ,@Valid @RequestBody ShoppingCartDto req){
         return new ResponseEntity<>(shoppingCartService.addItemToCart(jwt , req.getItemId() , req.getQuantity()) , HttpStatus.OK);
    }
    @GetMapping("/cart")
    public ResponseEntity<ShoppingCartResponse> addCartItem(@CookieValue("jwt") String jwt){
         return new ResponseEntity<>(shoppingCartService.GetAllShoppingCartItems(jwt) , HttpStatus.OK);
    }
    @PutMapping("/cart")
    public ResponseEntity<ApiResponse> updateItemSize(@CookieValue("jwt") String jwt ,@RequestBody ShoppingCartDto req){
         return new ResponseEntity<>(shoppingCartService.updateItemCart(jwt , req) , HttpStatus.OK);
    }
    @PutMapping("/cart/{id}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@CookieValue("jwt") String jwt ,@RequestBody ShoppingCartDto req , @PathVariable("id") Long id){
         return new ResponseEntity<>(shoppingCartService.updateItemQuantity(jwt , id , req.getQuantity()) , HttpStatus.OK);
    }
    @DeleteMapping("/cart/{id}")
    public ResponseEntity<ApiResponse> deleteItem(@CookieValue("jwt") String jwt , @PathVariable("id") Long id){
         return new ResponseEntity<>(shoppingCartService.deleteItem(jwt , id) , HttpStatus.OK);
    }
}
