package com.mroueh.mapper;


import com.mroueh.dto.SizeVariationDto;
import com.mroueh.entity.SizeVariation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SizeVariationMapper {

    SizeVariation toEntity(SizeVariationDto sizeVariationDto);

    SizeVariationDto toDto(SizeVariation sizeVariation );

    List<SizeVariationDto> toDtoList(List<SizeVariation> sizeVariationList);

    List<SizeVariation> toEntityList(List<SizeVariationDto> sizeVariationDtoList);
}
