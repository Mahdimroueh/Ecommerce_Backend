package com.mroueh.service.impl;

import com.mroueh.entity.ProductCategory;
import com.mroueh.entity.SizeCategory;
import com.mroueh.entity.SizeOption;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.SizeOptionMapper;
import com.mroueh.repository.SizeOptionRepository;
import com.mroueh.response.SizeOptionResponse;
import com.mroueh.service.ProductCategoryService;
import com.mroueh.service.SizeOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeOptionServiceImpl implements SizeOptionService {

    private  final SizeOptionRepository sizeOptionRepository;
    private final ProductCategoryService productCategoryService;
    private final SizeOptionMapper sizeOptionMapper;

    @Override
    public SizeOption getSizeOptionById(Long id) {
        return sizeOptionRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Size entity with the given id not found - "+id));
    }

    @Override
    public List<SizeOptionResponse> getAllOptionBasedOnProductCategory(Long productCategoryId) {

        ProductCategory category = productCategoryService.getProductCategoryById(productCategoryId);

        return sizeOptionMapper.toDtoList(category.getSizeCategory().getSizeOption()) ;

    }

    @Override
    public List<SizeOptionResponse> getAllSizeOption() {
        return sizeOptionMapper.toDtoList(sizeOptionRepository.findAll());
    }
}
