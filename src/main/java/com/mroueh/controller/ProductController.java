package com.mroueh.controller;

import com.mroueh.dto.ProductDto;
import com.mroueh.response.ApiResponse;
import com.mroueh.response.ProductResponse;
import com.mroueh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

//    @PostMapping("/admin/products")
//    public ResponseEntity<ApiResponse> addProduct(@RequestBody  ProductDto req) throws Exception {
//
//         return new ResponseEntity<>(productService.addProduct(req), HttpStatus.CREATED) ;
//
//    }
    @PostMapping("/admin/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody  ProductDto req) throws Exception {

         return new ResponseEntity<>(req, HttpStatus.CREATED) ;

    }

    @GetMapping("/products")
    public Page<ProductResponse> getFilteredProducts(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "brand" , required = false) String brand,
            @RequestParam(value = "color" , required = false) String color,
            @RequestParam(value="size" ,required = false) String size,
            @RequestParam(value="maxPrice" , required = false) Double maxPrice,
            @RequestParam(value="parentCategoryId" , required = false) Long parentCategoryId,
            @RequestParam(value= "page" , defaultValue = "0") int page) {

        return productService.getFilteredProducts(search, category, brand, color, size, maxPrice, parentCategoryId, page);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getSingleProduct(@PathVariable("id") Long id) throws Exception {
         return ResponseEntity.ok(productService.getProductById(id));
    }
}
