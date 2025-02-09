package com.mroueh.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListItemRequest {

    private Long colorVariationId;

    private Long sizeVariationId;
}
