package com.mroueh.service.impl;

import com.mroueh.dto.ColorVariationDto;
import com.mroueh.dto.ProductDto;
import com.mroueh.dto.SizeVariationDto;
import com.mroueh.entity.*;
import com.mroueh.mapper.ColorVariationMapper;
import com.mroueh.mapper.ProductMapper;
import com.mroueh.mapper.SizeVariationMapper;
import com.mroueh.repository.ColorRepository;
import com.mroueh.repository.ProductRepository;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductResponse;
import com.mroueh.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductCategoryService productCategoryService;
    private final BrandService brandService;
    private final ColorService colorService;
    private final ProductRepository productRepository;
    private final ImgurService imgurService;
    private final ProductMapper productMapper;
    private final ColorVariationService colorVariationService;


    private final ColorVariationMapper colorVariationMapper;
    private final SizeVariationMapper sizeVariationMapper;
    private final ColorRepository colorRepository;
    private final SizeOptionService sizeOptionService;



@Transactional
@Override
public ApiResponse addProduct(ProductDto req) throws Exception {

    Product product = productMapper.toEntity(req);

    ProductCategory category = productCategoryService.getProductCategoryById(req.getCategoryId());
    product.setProductCategory(category);

    Brand brand = brandService.getBrandById(req.getBrandId());
    product.setBrand(brand);

    List<String> imageUrls = new ArrayList<>();
    if(req.getImages().size() > 0) {
        for (String base64Image : req.getImages()) {
            String imageUrl = imgurService.uploadBase64Image(base64Image, product.getName(), product.getDesc());
            imageUrls.add(imageUrl);
        }
    }
    product.setImages(imageUrls);

    product.setColorVariations(colorVariationService.addProduct(req.getColorVariations() , product));

    productRepository.save(product);

    return new ApiResponse("Product added successfully" , true);
}

@Transactional
@Override
public ApiResponse addProductFaker(ProductDto req) throws Exception {
    Product product = productMapper.toEntity(req);

    ProductCategory category = productCategoryService.getProductCategoryById(req.getCategoryId());
    product.setProductCategory(category);

    Brand brand = brandService.getBrandById(req.getBrandId());
    product.setBrand(brand);

    List<ColorVariation> colorVariations = new ArrayList<>();
    for (ColorVariationDto tempColorVariationDto : req.getColorVariations()) {
        ColorVariation tempColorVariation = colorVariationMapper.toEntity(tempColorVariationDto);

        Color color = colorService.findById(tempColorVariationDto.getColorId());

        tempColorVariation.setColor(color);

        tempColorVariation.setProduct(product);

        tempColorVariation.setImages(tempColorVariationDto.getImages());

        List<SizeVariation> sizeVariations = new ArrayList<>();
        for (SizeVariationDto tempSizeVariationDto : tempColorVariationDto.getSizeVariations()) {
            SizeVariation currentSizeVariation = sizeVariationMapper.toEntity(tempSizeVariationDto);
            SizeOption sizeOption = sizeOptionService.getSizeOptionById(tempSizeVariationDto.getSizeOptionId());
            currentSizeVariation.setSizeOption(sizeOption);

            currentSizeVariation.setColorVariation(tempColorVariation);
            sizeVariations.add(currentSizeVariation);
        }
        tempColorVariation.setSizeVariations(sizeVariations);
        colorVariations.add(tempColorVariation);
    }
    product.setColorVariations(colorVariations);
    productRepository.save(product);

    return new ApiResponse("Product added successfully" , true);
}



    @Override
    public Page<ProductResponse> getFilteredProducts(String search, String category, String brand, String color, String size, Double maxPrice, Long parentCategoryId , int page) {
        Pageable pageable = PageRequest.of( page, 10);
        Page<Product> products = productRepository.findByFilters(search, category, brand, color, size, maxPrice, parentCategoryId,  pageable);
        return products.map(productMapper::toProductResponseDto);
    }
//    @Override
    public Page<ProductResponse> getFilteredProductsByUser(String jwt , String search, String category, String brand, String color, String size, Double maxPrice, Long parentCategoryId , int page) {
        Pageable pageable = PageRequest.of( page, 10);
        Page<Product> products = productRepository.findByFilters(search, category, brand, color, size, maxPrice, parentCategoryId,  pageable);
        return products.map(productMapper::toProductResponseDto);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.getById(id);
        return productMapper.toProductResponseDto(product);
    }
}
