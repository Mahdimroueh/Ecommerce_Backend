package com.mroueh.mapper;

import com.mroueh.entity.ProductCategory;
import com.mroueh.response.ProductCategoryResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

        ProductCategoryResponse toResponse(ProductCategory productCategory);

        List<ProductCategoryResponse> toResponseList(List<ProductCategory> productCategories);

}
