package com.mroueh.mapper;

import com.mroueh.entity.ShoppingCartItem;
import com.mroueh.entity.WishListItem;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishListItemMapper {


        @Mapping(target = "sizeId", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getId() : null)")
        @Mapping(target = "colorId", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getColorVariation().getId() : wishListItem.getColorVariation().getId())")
        @Mapping(target = "productName", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getColorVariation().getProduct().getName() : wishListItem.getColorVariation().getProduct().getName())")
        @Mapping(target = "unitPrice", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getColorVariation().getProduct().getSalePrice() : wishListItem.getColorVariation().getProduct().getSalePrice())")
        @Mapping(target = "color", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getColorVariation().getColor().getName() : wishListItem.getColorVariation().getColor().getName())")
        @Mapping(target = "images", expression = "java(wishListItem.getSizeVariation() != null ? wishListItem.getSizeVariation().getColorVariation().getImages() : wishListItem.getColorVariation().getImages())")
        WishListResponse toDto(WishListItem wishListItem);

        List<WishListResponse> toDtoList (List<WishListItem> wishListItem);
}
