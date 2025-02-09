package com.mroueh.mapper;

import com.mroueh.entity.Brand;
import com.mroueh.response.BrandResponse;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BrandMapper {


    BrandResponse toResponseDto(Brand brand);

    List<BrandResponse> toResponseDtoList(List<Brand> brand);
}
