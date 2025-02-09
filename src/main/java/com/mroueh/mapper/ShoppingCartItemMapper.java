package com.mroueh.mapper;

import com.mroueh.entity.ShoppingCartItem;
import com.mroueh.entity.WishListItem;
import com.mroueh.response.CartItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShoppingCartItemMapper {

    @Mapping(target = "productId", source = "sizeVariation.id")
    CartItemResponse toDto (ShoppingCartItem shoppingCartItem);


    @Mapping(target = "productId", source = "sizeVariation.id")
    @Mapping(target = "productName", source = "sizeVariation.colorVariation.product.name")
    @Mapping(target = "quantity", source = "sizeVariation.quantityInStock")
    @Mapping(target = "unitPrice", source = "sizeVariation.colorVariation.product.salePrice")
    @Mapping(target = "color", source = "sizeVariation.colorVariation.color.name")
    CartItemResponse toDto (WishListItem wishListItem);

//    @Mapping(target = "productId", source = "sizeVariation.id")
    List<CartItemResponse> toDto (List<WishListItem> wishListItem);
}
