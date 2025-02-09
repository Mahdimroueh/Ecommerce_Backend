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
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name ="product_category_id")
    private ProductCategory productCategory;

    private double salePrice;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;
    
    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<ColorVariation> colorVariations = new ArrayList<>();

    @Column(name = "product_description")
    private String desc;

    @ManyToOne
    @JoinColumn(name ="brand_id")
    @OneToMany(cascade = CascadeType.ALL)
    private Brand brand;

    @Column(name = "model_instructions")
    private String careInstructions;

    @Column(name = "about")
    private String about;
}

