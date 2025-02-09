package com.mroueh.service.impl;

import com.mroueh.dto.ColorVariationDto;
import com.mroueh.entity.*;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.ColorVariationMapper;
import com.mroueh.repository.ColorRepository;
import com.mroueh.repository.ColorVariationRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.service.ColorService;
import com.mroueh.service.ColorVariationService;
import com.mroueh.service.SizeVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ColorVariationServiceImpl implements ColorVariationService {

    private final ColorVariationMapper colorVariationMapper;
    private final ColorService colorService;
    private final ImgurService imgurService;
    private final SizeVariationService sizeVariationService;
    private final ColorVariationRepository colorVariationRepository;


    @Override
    public List<ColorVariation> addProduct(List<ColorVariationDto> colorVariationDtos , Product product) {
        List<ColorVariation> colorVariations = new ArrayList<>();

        for (ColorVariationDto tempColorVariationDto : colorVariationDtos) {
            ColorVariation tempColorVariation = colorVariationMapper.toEntity(tempColorVariationDto);
            Color color = colorService.findById(tempColorVariationDto.getColorId());
            tempColorVariation.setColor(color);
            tempColorVariation.setProduct(product);

            List<String> imageUrls = new ArrayList<>();
            if(!tempColorVariationDto.getImages().isEmpty()) {
                for (String base64Image : tempColorVariationDto.getImages()) {
                    String imageUrl = imgurService.uploadBase64Image(base64Image, product.getName(), product.getDesc());
                    imageUrls.add(imageUrl);
                }
            }
            tempColorVariation.setImages(imageUrls);

            tempColorVariation.setSizeVariations(sizeVariationService.addProduct(tempColorVariationDto.getSizeVariations() , tempColorVariation ));

            colorVariations.add(tempColorVariation);
        }
       return colorVariations;
    }

    @Override
    public ColorVariation getColorVariationById(Long id) {
        return colorVariationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Item is Not Present"));
    }

}
