package com.mroueh.service.impl;

import com.mroueh.dto.SizeVariationDto;
import com.mroueh.entity.ColorVariation;
import com.mroueh.entity.SizeOption;
import com.mroueh.entity.SizeVariation;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.SizeVariationMapper;
import com.mroueh.repository.ColorRepository;
import com.mroueh.repository.ColorVariationRepository;
import com.mroueh.repository.SizeVariationRepository;
import com.mroueh.response.AvailableSize;
import com.mroueh.service.SizeOptionService;
import com.mroueh.service.SizeVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SizeVariationServiceImpl implements SizeVariationService {

    private final SizeVariationMapper sizeVariationMapper;
    private final SizeOptionService sizeOptionService;
    private final SizeVariationRepository sizeVariationRepository;
    private final ColorRepository colorRepository;
    private final ColorVariationRepository colorVariationRepository;

    @Override
    public List<SizeVariation> addProduct(List<SizeVariationDto> sizeVariationDtos , ColorVariation colorVariation) {
        List<SizeVariation> sizeVariations = new ArrayList<>();
        for (SizeVariationDto tempSizeVariationDto : sizeVariationDtos) {

            SizeVariation currentSizeVariation = sizeVariationMapper.toEntity(tempSizeVariationDto);

            SizeOption sizeOption = sizeOptionService.getSizeOptionById(tempSizeVariationDto.getSizeOptionId());
            currentSizeVariation.setSizeOption(sizeOption);

            // Set parent ColorVariation
            currentSizeVariation.setColorVariation(colorVariation);

            // Add to list
            sizeVariations.add(currentSizeVariation);
        }
        return sizeVariations;
    }

    @Override
    public SizeVariation getByID(Long id) {
        return sizeVariationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Item is Not Present"));
    }

    @Override
    public List<AvailableSize> getAvailableSize(Long sizeId , Long colorId) {
      List<AvailableSize> availableSizes = new ArrayList<>();
      SizeVariation size;
      ColorVariation color = new ColorVariation();
      if(sizeId != null){
         size = getByID(sizeId);
         color = size.getColorVariation();
      }else if(colorId != null){
          color = colorVariationRepository.getReferenceById(colorId);
      }

        return color.getSizeVariations().stream()
                .map(sizeVariation -> {
                    AvailableSize curSize = new AvailableSize();
                    curSize.setId(sizeVariation.getId());
                    curSize.setSize(sizeVariation.getSizeOption().getName());
                    return curSize;
                })
                .collect(Collectors.toList());
    }
}
