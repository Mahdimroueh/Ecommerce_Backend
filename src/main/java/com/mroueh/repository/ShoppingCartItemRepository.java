package com.mroueh.repository;

import com.mroueh.entity.ShoppingCart;
import com.mroueh.entity.ShoppingCartItem;
import com.mroueh.entity.SizeVariation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem,Long> {

    ShoppingCartItem findByCartIdAndSizeVariationId(Long cartId, Long sizeVariationId);


    ShoppingCartItem findBySizeVariationId(Long sizeVariationId);
}
