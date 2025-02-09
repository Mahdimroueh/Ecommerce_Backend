package com.mroueh.dto;

import com.mroueh.entity.Brand;
import com.mroueh.entity.ColorVariation;
import com.mroueh.entity.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

    private String name;

    private Long categoryId;

    private double salePrice;

    private List<String> images;

    private Long brandId;

    private List<ColorVariationDto> colorVariations = new ArrayList<>();

    private String desc;

    private String careInstructions;

    private String about;
}
