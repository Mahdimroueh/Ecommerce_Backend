package com.mroueh.service.impl;

import com.mroueh.dto.WishListItemRequest;
import com.mroueh.entity.*;
import com.mroueh.exception.BadRequestException;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.ShoppingCartItemMapper;
import com.mroueh.mapper.WishListItemMapper;
import com.mroueh.repository.UserRepository;
import com.mroueh.repository.WishListItemRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.CartItemResponse;
import com.mroueh.response.WishListResponse;
import com.mroueh.service.ColorVariationService;
import com.mroueh.service.SizeVariationService;
import com.mroueh.service.UserService;
import com.mroueh.service.WishListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishListService {

    private final UserService userService;
    private final ColorVariationService colorVariationService;
    private final SizeVariationService sizeVariationService;
    private final JwtService jwtService;
    private final WishListItemRepository wishListItemRepository;
    private final WishListItemMapper wishListItemMapper ;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApiResponse addItem(String jwt , WishListItemRequest req) {
        User user = userService.getUserByUsername(jwtService.getUsername(jwt));
        WishListItem item = new WishListItem();
        item.setUser(user);
        if(req.getColorVariationId() != null){
            if(wishListItemRepository.findByColorVariationId(req.getColorVariationId()) != null){
                throw new BadRequestException("already added");
            }
            ColorVariation colorVariation = colorVariationService.getColorVariationById(req.getColorVariationId());
            item.setColorVariation(colorVariation);
        }else if(req.getSizeVariationId()!=null){
            if(wishListItemRepository.findBySizeVariationId(req.getSizeVariationId()) != null){
                throw new BadRequestException("already added");
            }
            SizeVariation sizeVariation = sizeVariationService.getByID(req.getSizeVariationId());
            item.setSizeVariation(sizeVariation);
        }else{
            throw new BadRequestException("you must provide a colorVariation or sizeVariation Id");
        }

        wishListItemRepository.save(item);
        return new ApiResponse("Item added to wishlist successfully", true);
    }
    @Override
    public  List<WishListResponse> GetAllShoppingCartItems(String jwt) {
        String username = jwtService.getUsername(jwt);
        List<WishListItem> wishListItems = userService.getUserByUsername(username).getWishListItems();
        List<WishListResponse> wishListResponseList = wishListItemMapper.toDtoList(wishListItems);

        for (WishListResponse temp : wishListResponseList) {
            if(temp.getSizeId() == null){
                temp.setSizes(sizeVariationService.getAvailableSize(null , temp.getColorId()));
            }else{
                temp.setSizes(sizeVariationService.getAvailableSize(temp.getSizeId() , null));
            }
        }
         return wishListResponseList;
    }

    @Override
    @Transactional
    public ApiResponse deleteItem(String jwt, Long sizeId, Long colorId) {
        String username = jwtService.getUsername(jwt);
        List<WishListItem> wishListItems = userService.getUserByUsername(username).getWishListItems();

        Optional<WishListItem> itemToDelete;

            if (sizeId != null) {
                itemToDelete = wishListItems.stream()
                        .filter(item ->  item.getSizeVariation() != null && item.getSizeVariation().getId().equals(sizeId)).findFirst();

            } else {
                itemToDelete = wishListItems.stream()
                        .filter(item -> item.getColorVariation() != null && item.getColorVariation().getId().equals(colorId))
                        .findFirst();
            }

            if (itemToDelete.isPresent()) {
               User user = userService.getUserByUsername(username);
               user.setWishListItems(wishListItems);
               wishListItems.forEach(wishListItem -> wishListItem.setUser(user));;
                wishListItems.remove(itemToDelete.get());
               userRepository.save(user);

                return new ApiResponse( "Item successfully removed from wishlist." , true);

            } else {
                return new ApiResponse("Item not found in wishlist." , false);
            }
     }

    @Override
    @Transactional
    public ApiResponse updateSize(String jwt, Long id, Long newSizeId, boolean isSize) {
        String username = jwtService.getUsername(jwt);
        User user = userService.getUserByUsername(username);
        List<WishListItem> wishListItems = user.getWishListItems();
        SizeVariation newSize = sizeVariationService.getByID(newSizeId);

        Optional<WishListItem> itemToUpdate = wishListItems.stream()
                .filter(item -> isSize
                        ? (item.getSizeVariation() != null && item.getSizeVariation().getId().equals(id))
                        : (item.getColorVariation() != null && item.getColorVariation().getId().equals(id)))
                .findFirst();

        itemToUpdate.ifPresentOrElse(item -> {
            item.setSizeVariation(newSize);
            item.setColorVariation(null);
            userRepository.saveAndFlush(user);
        }, () -> {
            throw new EntityNotFoundException("Item not found in wishlist.");
        });

        return new ApiResponse("Size updated successfully.", true);
    }

}

