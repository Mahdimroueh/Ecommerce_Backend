package com.mroueh.service.impl;

import com.mroueh.dto.ShoppingCartDto;
import com.mroueh.entity.*;
import com.mroueh.exception.InsufficientQuantityException;
import com.mroueh.mapper.ShoppingCartMapper;
import com.mroueh.repository.ShoppingCartItemRepository;
import com.mroueh.repository.ShoppingCartRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.ShoppingCartResponse;
import com.mroueh.service.ShoppingCartService;
import com.mroueh.service.SizeVariationService;
import com.mroueh.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;
    private final UserService userService;
    private final JwtService jwtService;
    private final SizeVariationService sizeVariationService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ApiResponse addItemToCart(String jwt, Long sizeVariationId, int quantity) {

        String username = jwtService.getUsername(jwt);
        ShoppingCart cart = userService.getUserByUsername(username).getCart();
        SizeVariation sizeVariation = sizeVariationService.getByID(sizeVariationId);
        ColorVariation colorVariation = sizeVariation.getColorVariation();
        Product product = colorVariation.getProduct();
        int remainingStock = sizeVariation.getQuantityInStock();
        ShoppingCartItem item = shoppingCartItemRepository.findByCartIdAndSizeVariationId(cart.getId(), sizeVariationId);
        double price = product.getSalePrice();

        if (item != null) {
            int pastQuantity = item.getQuantity();
            if ((item.getQuantity() + quantity) > remainingStock) {
                String res = (remainingStock - pastQuantity) == 0 ? "Out of Stock" : "Not enough items you can only add: " + (remainingStock - pastQuantity);
                throw new InsufficientQuantityException(res);
            }

            item.setQuantity(pastQuantity + quantity);
        } else {
            if (quantity > remainingStock) {
                throw new InsufficientQuantityException("Not enough items, you can only add: " + remainingStock);
            }
            double newPrice = price * quantity;
            ShoppingCartItem newItem = new ShoppingCartItem();
            newItem.setCart(cart);
            newItem.setSizeVariation(sizeVariation);
            newItem.setQuantity(quantity);
            newItem.setImage(colorVariation.getImages().get(0));
            newItem.setUnitPrice(price);
            newItem.setProductName(product.getName());
            newItem.setColor(colorVariation.getColor().getName());
            cart.addItem(newItem);
        }
        shoppingCartRepository.save(cart);
        return new ApiResponse("Item added successfully", true);
    }

    @Transactional
    public ApiResponse updateItemCart(String jwt, ShoppingCartDto req) {
        String username = jwtService.getUsername(jwt);
        ShoppingCart cart = userService.getUserByUsername(username).getCart();
        SizeVariation newSizeVariation = sizeVariationService.getByID(req.getNewSizeId());
        int remainingStock = newSizeVariation.getQuantityInStock();
        ShoppingCartItem item = shoppingCartItemRepository.findBySizeVariationId(req.getItemId());
        ShoppingCartItem isPresent = shoppingCartItemRepository.findBySizeVariationId(req.getNewSizeId());

        if (isPresent != null) {
            SizeVariation existingSizeVariation = sizeVariationService.getByID(req.getNewSizeId());
            if (existingSizeVariation.getQuantityInStock() < item.getQuantity() + isPresent.getQuantity()) {
                throw new InsufficientQuantityException("Only " + remainingStock + " item is available");
            }
            isPresent.setQuantity(item.getQuantity() + isPresent.getQuantity());
            cart.getShoppingCartItems().remove(item);
        } else {
            if (item.getQuantity() > remainingStock) {
                throw new InsufficientQuantityException("Only " + remainingStock + " item is available");
            }
            item.setSizeVariation(newSizeVariation);
        }

        shoppingCartRepository.save(cart);
        return new ApiResponse("Item updated successfully", true);
    }

    @Override
    @Transactional
    public ApiResponse updateItemQuantity(String jwt, Long id, int quantity) {
        String username = jwtService.getUsername(jwt);
        ShoppingCart cart = userService.getUserByUsername(username).getCart();
        SizeVariation sizeVariation = sizeVariationService.getByID(id);
        int remainingStock = sizeVariation.getQuantityInStock();
        ShoppingCartItem item = shoppingCartItemRepository.findBySizeVariationId(id);
        if (quantity > remainingStock) {
            throw new InsufficientQuantityException("Only " + remainingStock + " item is available");
        }

        item.setQuantity(quantity);

        shoppingCartRepository.save(cart);
        return new ApiResponse("Item updated successfully", true);
    }

    @Override
    @Transactional
    public ApiResponse deleteItem(String jwt, Long id) {
        String username = jwtService.getUsername(jwt);
        ShoppingCart cart = userService.getUserByUsername(username).getCart();
        ShoppingCartItem item = shoppingCartItemRepository.findBySizeVariationId(id);
        int quantity = item.getQuantity();

        cart.getShoppingCartItems().remove(item);

        shoppingCartRepository.save(cart);
        return new ApiResponse("Successfully deleted", true);
    }

    @Override
    public ShoppingCartResponse GetAllShoppingCartItems(String jwt) {
        String username = jwtService.getUsername(jwt);
        ShoppingCart cart = userService.getUserByUsername(username).getCart();

        ShoppingCartResponse response = shoppingCartMapper.toDto(cart);
        for (CartItemResponse temp : response.getShoppingCartItems()) {
            temp.setSizes(sizeVariationService.getAvailableSize(temp.getProductId()));
        }

        return response;
    }

}