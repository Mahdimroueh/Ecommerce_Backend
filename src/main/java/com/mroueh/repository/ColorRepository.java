package com.mroueh.repository;

import com.mroueh.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color , Long> {


     Color findColorByName(String name);
}
