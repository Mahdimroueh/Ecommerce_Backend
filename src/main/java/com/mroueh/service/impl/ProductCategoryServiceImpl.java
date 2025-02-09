package com.mroueh.service.impl;

import com.mroueh.entity.ProductCategory;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.CategoryMapper;
import com.mroueh.repository.ProductCategoryRepository;
import com.mroueh.response.ProductCategoryResponse;
import com.mroueh.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private  final ProductCategoryRepository productCategoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public ProductCategory getProductCategoryById(Long id) {
                return productCategoryRepository.findById(id)
                        .orElseThrow(
                                ()-> new EntityNotFoundException("Category with the given id not found - "+id));
    }
    @Override
    public List<ProductCategoryResponse> getAllProductCategories() {

        List<ProductCategory> rootCategories = productCategoryRepository.findByParentCategoryIsNull();
        return categoryMapper.toResponseList(rootCategories);

    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategoriesByParent(Long id) {
        ProductCategory parentCategory = getProductCategoryById(id);
        return categoryMapper.toResponseList(productCategoryRepository.findByParentCategory(parentCategory));
    }

    @Override
    public List<ProductCategoryResponse> getParentCategories() {
          return categoryMapper.toResponseList(productCategoryRepository.findByParentCategoryIsNull());
    }


}
