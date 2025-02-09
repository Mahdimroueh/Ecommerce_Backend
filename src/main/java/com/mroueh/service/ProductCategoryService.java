package com.mroueh.service;

import com.mroueh.entity.ProductCategory;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory getProductCategoryById(Long id);

//    ApiResponse addCategory(ProductCategoryDto req);

    List<ProductCategoryResponse> getAllProductCategories();

    List<ProductCategoryResponse> getAllProductCategoriesByParent(Long id);


    List<ProductCategoryResponse> getParentCategories();
}
