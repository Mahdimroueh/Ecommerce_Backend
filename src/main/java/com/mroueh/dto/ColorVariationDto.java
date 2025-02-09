package com.mroueh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ColorVariationDto {

    private List<String> images = new ArrayList<>();

    private Long colorId;

    private boolean isFavorite;

    private List<SizeVariationDto> sizeVariations = new ArrayList<>();
}
