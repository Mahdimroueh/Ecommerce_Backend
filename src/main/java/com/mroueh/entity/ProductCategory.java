package com.mroueh.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Column(name = "category_image")
    private String image;

    @Column(name = "category_description")
    private String desc;

    @ManyToOne
    @JoinColumn(name = "size_category_id")
    @JsonManagedReference
    private SizeCategory sizeCategory;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory" , fetch = FetchType.LAZY)
    List<ProductCategory> subCategories = new ArrayList<>();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "image = " + image + ", " +
                "desc = " + desc + ", " +
                "sizeCategory = " + sizeCategory + ", " +
                "parentCategory = " + parentCategory + ")";
    }
}
