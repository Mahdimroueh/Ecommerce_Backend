package com.mroueh.service.impl;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.entity.*;
import com.mroueh.exception.BadRequestException;
import com.mroueh.mapper.ShoppingCartItemMapper;
import com.mroueh.repository.WishListItemRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListItemResponse;
import com.mroueh.service.ColorVariationService;
import com.mroueh.service.SizeVariationService;
import com.mroueh.service.UserService;
import com.mroueh.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final UserService userService;
    private final ColorVariationService colorVariationService;
    private final SizeVariationService sizeVariationService;
    private final JwtService jwtService;
    private final WishListItemRepository wishListItemRepository;
//    private final ShoppingCartItem shoppingCartItem;
    private final ShoppingCartItemMapper shoppingCartItemMapper;

    @Override
    public ApiResponse addItem(String jwt , WishListItemRequest req) {
        User user = userService.getUserByUsername(jwtService.getUsername(jwt));
        WishListItem item = new WishListItem();
        item.setUser(user);
        if(req.getColorVariationId() != null){
            ColorVariation colorVariation = colorVariationService.getColorVariationById(req.getColorVariationId());
            item.setColorVariation(colorVariation);
        }else if(req.getSizeVariationId()!=null){
            SizeVariation sizeVariation = sizeVariationService.getByID(req.getSizeVariationId());
            item.setSizeVariation(sizeVariation);
        }else{
            throw new BadRequestException("you must provide a colorVariation or sizeVariation Id");
        }

        wishListItemRepository.save(item);
        return new ApiResponse("Item added to wishlist successfully", true);
    }
    @Override
    public  List<CartItemResponse> GetAllShoppingCartItems(String jwt) {
        String username = jwtService.getUsername(jwt);
        List<WishListItem> wishListItems = userService.getUserByUsername(username).getWishListItems();
        List<CartItemResponse> cartItemResponses =shoppingCartItemMapper.toDto(wishListItems);

        for (CartItemResponse temp : cartItemResponses) {
            temp.setSizes(sizeVariationService.getAvailableSize(temp.getProductId()));
        }
         return shoppingCartItemMapper.toDto(wishListItems);
    }
}
