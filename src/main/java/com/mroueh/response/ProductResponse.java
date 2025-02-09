package com.mroueh.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class ProductResponse {
    private Long id;

    private String name;

    private String CategoryName;

    private double salePrice;

    private List<String> images;

    private String BrandName;

    private List<ColorVariationResponse> colorVariationsResponses = new ArrayList<>();

    private String desc;

    private String careInstructions;

    private String about;
}
