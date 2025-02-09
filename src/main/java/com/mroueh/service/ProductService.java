package com.mroueh.service;

import com.mroueh.dto.ProductDto;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    public ApiResponse addProduct(ProductDto req) throws Exception;

    public Page<ProductResponse> getFilteredProducts(String search, String category, String brand, String color, String size, Double maxPrice, Long parentCategory ,  int page);

    public ProductResponse getProductById(Long id);

    public ApiResponse addProductFaker(ProductDto req) throws Exception;
}
