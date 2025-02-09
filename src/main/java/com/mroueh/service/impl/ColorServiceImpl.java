package com.mroueh.service.impl;

import com.mroueh.entity.Color;
import com.mroueh.exception.EntityNotFoundException;
import com.mroueh.mapper.ColorMapper;
import com.mroueh.repository.ColorRepository;
import com.mroueh.response.ColorResponse;
import com.mroueh.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;
    @Override
    public List<ColorResponse> getAllColor() {
        return colorMapper.toDtoList(colorRepository.findAll());
    }

    @Override
    public Color findById(Long id) {
        return colorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No such color"));
    }
}
