package com.mroueh.repository;

import com.mroueh.entity.SizeVariation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariationRepository extends JpaRepository<SizeVariation, Long> {

}
