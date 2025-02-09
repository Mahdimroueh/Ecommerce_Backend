package com.mroueh.controller;


import com.mroueh.response.SizeOptionResponse;
import com.mroueh.service.SizeOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SIzeOptionController {

    private final SizeOptionService sizeOptionService;


    @GetMapping("/options/{categoryId}")
    public ResponseEntity<List<SizeOptionResponse>> getAllOptionsBasedOnCategory(@PathVariable Long categoryId) {
        List<SizeOptionResponse> sizeOptions = sizeOptionService.getAllOptionBasedOnProductCategory(categoryId);
        return ResponseEntity.ok(sizeOptions);
    }
    @GetMapping("/options")
    public ResponseEntity<List<SizeOptionResponse>> getAllOptions() {
        List<SizeOptionResponse> sizeOptions = sizeOptionService.getAllSizeOption();
        return ResponseEntity.ok(sizeOptions);
    }

}
