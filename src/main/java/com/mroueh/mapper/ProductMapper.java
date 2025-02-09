package com.mroueh.mapper;


import com.mroueh.response.ColorVariationResponse;
import com.mroueh.dto.ProductDto;
import com.mroueh.entity.ColorVariation;
import com.mroueh.entity.Product;
import com.mroueh.entity.SizeVariation;
import com.mroueh.response.ProductResponse;
import com.mroueh.response.SizeVariationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" , uses = {ColorVariationMapper.class})
public interface ProductMapper {

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productList);

    @Mapping(source = "productCategory.name", target = "categoryName")
    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(source = "colorVariations", target = "colorVariationsResponses")
    ProductResponse toProductResponseDto(Product product);

    List<ProductResponse> toProductResponseList(List<Product> products);

    @Mapping(source = "color.name", target = "colorName")
    @Mapping(source = "sizeVariations", target = "sizeVariationsResponses")
    ColorVariationResponse toColorVariationsResponse(ColorVariation colorVariation);

    List<ColorVariationResponse> toColorVariationsResponse(List<ColorVariation> colorVariations);

    @Mapping(source = "sizeOption.id", target = "sizeOptionId")
    @Mapping(source = "sizeOption.name", target = "sizeName")
    SizeVariationResponse toSizeVariationsResponse(SizeVariation sizeVariation);


    List<SizeVariationResponse> toSizeVariationsResponseList(List<SizeVariation> sizeVariations);
}
