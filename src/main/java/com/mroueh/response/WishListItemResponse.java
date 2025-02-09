package com.mroueh.response;

import com.mroueh.entity.ShoppingCartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListItemResponse {

    private List<CartItemResponse> wishListItems  = new ArrayList<>();

}


