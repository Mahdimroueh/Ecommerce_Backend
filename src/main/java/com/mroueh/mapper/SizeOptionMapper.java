package com.mroueh.mapper;


import com.mroueh.entity.SizeOption;
import com.mroueh.response.SizeOptionResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SizeOptionMapper {


    SizeOptionResponse toDto(SizeOption sizeOption);

    List<SizeOptionResponse> toDtoList(List<SizeOption> sizeOptionList);
}
