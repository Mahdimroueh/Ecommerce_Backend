package com.mroueh.service.impl;

import com.mroueh.entity.SizeCategory;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.repository.SizeCategoryRepository;
import com.mroueh.service.SizeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeCategoryServiceImpl implements SizeCategoryService {

    private final SizeCategoryRepository sizeCategoryRepository;

    @Override
    public SizeCategory findById(Long id) {
        return sizeCategoryRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Category with the given id not found - "+id));
    }
}
