package com.mroueh.service;

import com.mroueh.entity.Brand;
import com.mroueh.response.BrandResponse;

import java.util.List;

public interface BrandService {

    Brand getBrandById(Long id);

    List<BrandResponse> getAllBrand();
}
