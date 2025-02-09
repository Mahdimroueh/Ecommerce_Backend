package com.mroueh.controller;


import com.mroueh.response.ColorResponse;
import com.mroueh.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/colors")
    public ResponseEntity<List<ColorResponse>> getAllColor(){

        return ResponseEntity.ok(colorService.getAllColor());
    }
}
