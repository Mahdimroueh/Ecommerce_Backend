package com.mroueh.controller;

import com.mroueh.response.BrandResponse;
import com.mroueh.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService  brandService;

    @GetMapping("/brand")
    public ResponseEntity<List<BrandResponse>> getAllBrand(){
        return new ResponseEntity<>(brandService.getAllBrand() , HttpStatus.OK);
    }
}
