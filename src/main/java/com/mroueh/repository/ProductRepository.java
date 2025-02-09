package com.mroueh.repository;

import com.mroueh.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN p.productCategory pc " +
            "LEFT JOIN p.brand b " +
            "LEFT JOIN p.colorVariations cv " +
            "LEFT JOIN cv.color c " +
            "LEFT JOIN cv.sizeVariations sv " +
            "LEFT JOIN sv.sizeOption s " +
            "WHERE " +
            "(:search IS NULL or LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))  or LOWER(pc.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.desc) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND (:category IS NULL OR LOWER(pc.name) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:brand IS NULL OR LOWER(b.name) LIKE LOWER(CONCAT('%', :brand, '%'))) " +
            "AND (:color IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:size IS NULL OR LOWER(s.name) LIKE LOWER(CONCAT('%', :size, '%'))) " +
            "AND (:parentCategoryId IS NULL OR pc.parentCategory.id=:parentCategoryId) " +
            "AND (:maxPrice IS NULL OR (p.salePrice <= :maxPrice))")

    Page<Product> findByFilters(
            @Param("search") String search,
            @Param("category") String category,
            @Param("brand") String brand,
            @Param("color") String color,
            @Param("size") String size,
            @Param("maxPrice") Double maxPrice,
            @Param("parentCategoryId") Long parentCategoryId,
            Pageable pageable);
}
