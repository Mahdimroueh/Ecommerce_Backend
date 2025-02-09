package com.mroueh.service;

import com.mroueh.entity.Color;
import com.mroueh.response.ColorResponse;

import java.util.List;

public interface ColorService {

    List<ColorResponse> getAllColor ();

    Color findById(Long id);
}
