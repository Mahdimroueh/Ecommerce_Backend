package com.mroueh.mapper;


import com.mroueh.entity.ShoppingCart;
import com.mroueh.entity.ShoppingCartItem;
import com.mroueh.response.ShoppingCartResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = {ShoppingCartItemMapper.class})
public interface ShoppingCartMapper {


    ShoppingCartResponse toDto (ShoppingCart shoppingCart);
}
