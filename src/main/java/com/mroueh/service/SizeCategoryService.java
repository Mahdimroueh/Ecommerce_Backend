package com.mroueh.service;

import com.mroueh.entity.SizeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeCategoryService {

    SizeCategory findById(Long id);
}
