package com.mroueh.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="size_category")
public class SizeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @OneToMany(mappedBy = "sizeCategory")
    private List<SizeOption> sizeOption = new ArrayList<>();

    @OneToMany(mappedBy = "sizeCategory")
    private List<ProductCategory> product = new ArrayList<>();
}
