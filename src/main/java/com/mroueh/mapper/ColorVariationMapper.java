package com.mroueh.mapper;

import com.mroueh.dto.ColorVariationDto;
import com.mroueh.entity.ColorVariation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {SizeVariationMapper.class})
public interface ColorVariationMapper {

    ColorVariation toEntity(ColorVariationDto colorDto);

    @Mapping(source = "color.id", target = "colorId")
    ColorVariationDto toDto(ColorVariation colorVariation);

    List<ColorVariationDto> toDtoList(List<ColorVariation> colorVariationList);
}
