package com.mroueh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SizeVariationDto {

    private Long sizeOptionId;

    private int quantityInStock;

    private boolean isFavorite;

    private String code;

}
