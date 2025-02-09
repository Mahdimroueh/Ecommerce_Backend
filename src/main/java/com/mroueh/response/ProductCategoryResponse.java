package com.mroueh.response;


import com.mroueh.entity.ProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryResponse {

    private Long id;

    private String name;

    private List<ProductCategoryResponse> subCategories;
}
