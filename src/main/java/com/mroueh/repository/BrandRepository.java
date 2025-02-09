package com.mroueh.repository;

import com.mroueh.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand , Long> {

   Brand findByName(String name);
}
