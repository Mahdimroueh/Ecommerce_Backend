package com.mroueh.service;

import com.mroueh.dto.ColorVariationDto;
import com.mroueh.entity.ColorVariation;
import com.mroueh.entity.Product;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductResponse;

import java.util.List;

public interface ColorVariationService {

    public List<ColorVariation> addProduct(List<ColorVariationDto> colorVariationDtos , Product product);

    public ColorVariation getColorVariationById(Long id);
}
