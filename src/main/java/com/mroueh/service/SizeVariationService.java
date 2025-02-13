package com.mroueh.service;

import com.mroueh.dto.SizeVariationDto;
import com.mroueh.entity.ColorVariation;
import com.mroueh.entity.SizeVariation;
import com.mroueh.response.AvailableSize;

import java.util.List;

public interface SizeVariationService {

    public List<SizeVariation> addProduct(List<SizeVariationDto> sizeVariationDtos , ColorVariation colorVariation);

    public SizeVariation getByID(Long id);

    public List<AvailableSize> getAvailableSize(Long sizeId , Long colorId);

}
