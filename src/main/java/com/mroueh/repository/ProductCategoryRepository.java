package com.mroueh.repository;

import com.mroueh.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory , Long> {

       List<ProductCategory> findByParentCategoryIsNull();

       List<ProductCategory> findByParentCategory(ProductCategory parentCategory);




}
