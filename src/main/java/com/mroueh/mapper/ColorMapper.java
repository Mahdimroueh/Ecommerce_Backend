package com.mroueh.mapper;


import com.mroueh.entity.Color;
import com.mroueh.response.ColorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ColorMapper {

    ColorResponse toDto (Color color);


    List<ColorResponse> toDtoList (List<Color> colors);
}
