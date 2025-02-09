package com.mroueh.service.impl;

import com.mroueh.entity.Brand;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.BrandMapper;
import com.mroueh.repository.BrandRepository;
import com.mroueh.response.BrandResponse;
import com.mroueh.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImp implements BrandService {

    private  final BrandRepository brandRepository;
    private final BrandMapper mapper;

    @Override
    public Brand getBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Brand entity with the given id not found - "+ id));
    }

    @Override
    public List<BrandResponse> getAllBrand() {
        List<Brand> brand = brandRepository.findAll();
        return mapper.toResponseDtoList(brand);
    }
}
