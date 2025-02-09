package com.mroueh.service;

import com.mroueh.entity.SizeOption;
import com.mroueh.response.SizeOptionResponse;

import java.util.List;

public interface SizeOptionService {

    SizeOption getSizeOptionById(Long id);

    List<SizeOptionResponse> getAllOptionBasedOnProductCategory(Long id);


    List<SizeOptionResponse> getAllSizeOption();
}
