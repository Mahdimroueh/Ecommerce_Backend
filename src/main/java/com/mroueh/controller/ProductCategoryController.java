package com.mroueh.controller;


import com.mroueh.entity.ProductCategory;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductCategoryResponse;
import com.mroueh.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCategoryController {

      private final ProductCategoryService productCategoryService;

//    @PostMapping("admin/categories")
//    public ResponseEntity<ApiResponse> addCategory(@RequestBody ProductCategoryDto req){
//
//        return new ResponseEntity<>(productCategoryService.addCategory(req), HttpStatus.CREATED);
//
//    }

    @GetMapping("parent/categories")
    public ResponseEntity<List<ProductCategoryResponse>> getParentCategory(){
        return new ResponseEntity<>(productCategoryService.getAllProductCategories(), HttpStatus.OK);

    }
    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryResponse>> getAllCategory(){
        return new ResponseEntity<>(productCategoryService.getAllProductCategories(), HttpStatus.OK);

    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<ProductCategoryResponse>> getAllCategoryByParent(@PathVariable("id") Long id){
        return new ResponseEntity<>(productCategoryService.getAllProductCategoriesByParent(id), HttpStatus.OK);

    }

}
