package com.mroueh.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartDto {

    @Min(1)
    private Long itemId;

    private int quantity=1;

    private Long newSizeId;
}
