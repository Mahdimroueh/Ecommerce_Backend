package com.mroueh.config;
import com.github.javafaker.Faker;
import com.mroueh.dto.ProductDto;
import com.mroueh.dto.ColorVariationDto;
import com.mroueh.dto.SizeVariationDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FakeDataGenerator {

    private final Faker faker = new Faker();
    private final Random random = new Random();
    List<String> imageUrls = new ArrayList<>(List.of(
            "https://images.asos-media.com/products/pullbear-sweatshirt-in-light-blue/207212991-1-lightblue?$n_320w$&wid=317&fit=constrain",
            "https://images.asos-media.com/products/pullbear-sweatshirt-in-light-blue/207212991-2?$n_320w$&wid=317&fit=constrain"
    ));


    public ProductDto generateProductDto() {
        ProductDto productDto = new ProductDto();
        productDto.setName(faker.commerce().productName());
        long randomCategory = 3 + random.nextInt(44);
        productDto.setCategoryId(randomCategory);
        long randomBrand = 1+random.nextInt(31);
        productDto.setBrandId(randomBrand);
        productDto.setDesc(faker.lorem().paragraph());
        productDto.setCareInstructions(faker.lorem().sentence());
        productDto.setAbout(faker.lorem().sentence());
        productDto.setSalePrice(Double.parseDouble(faker.commerce().price(100.0, 500.0))); // Original Price

        List<String> images = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            images.add(imageUrls.get(i)); // Add the image URL to the set
        }
        Collections.shuffle(imageUrls);
        productDto.setImages(images);

        int colorVariationCount = faker.number().numberBetween(1, 4); // Between 1 and 3 variations
        List<ColorVariationDto> colorVariations = new ArrayList<>();
        for (int i = 0; i < colorVariationCount; i++) {
            colorVariations.add(generateColorVariationDto());
        }
        productDto.setColorVariations(colorVariations);

        return productDto;
    }

    public ColorVariationDto generateColorVariationDto() {
        List<String> imageUrls = new ArrayList<>(List.of(
                "https://content-management-files.canva.com/cdn-cgi/image/f=auto,q=70/2fdbd7ab-f378-4c63-8b21-c944ad2633fd/header_t-shirts2.jpg",
                "https://nobero.com/cdn/shop/files/og.jpg?v=1722234051",
                "https://chriscross.in/cdn/shop/files/Chris_Cross_Royal_Blue_Tshirt.jpg?v=1723276250&width=2048",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqwwt1h-wv_c0v1RncZ72ljzUNZIvv4WLdOQ&s",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROY84097irVpIvoiPwVaEB3G-mXQuprfaADQ&s"
        ));
        ColorVariationDto colorVariationDto = new ColorVariationDto();

        long colorId = 1L + random.nextInt(24);
        colorVariationDto.setColorId(colorId);

        int imageCount = faker.number().numberBetween(1, 5);

        Collections.shuffle(imageUrls);

        List<String> images = new ArrayList<>();

        for (int i = 0; i < imageCount; i++) {
            images.add(imageUrls.get(i));
        }
        colorVariationDto.setImages(images);

        List<SizeVariationDto> sizeVariations = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            sizeVariations.add(generateSizeVariationDto(i));
        }
        colorVariationDto.setSizeVariations(sizeVariations);

        return colorVariationDto;
    }

    public SizeVariationDto generateSizeVariationDto(int id) {
        SizeVariationDto sizeVariationDto = new SizeVariationDto();
        sizeVariationDto.setSizeOptionId((long) id); // Random Size Option ID
        sizeVariationDto.setQuantityInStock(faker.number().numberBetween(0, 100)); // Quantity in Stock
        sizeVariationDto.setCode(faker.code().isbn13()); // Random Code

        return sizeVariationDto;
    }
}
