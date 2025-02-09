package com.mroueh.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="color_variation")
public class ColorVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;

     @ManyToOne
     @JoinColumn(name = "color_id")
     private Color color;

     @ElementCollection
     @CollectionTable(name = "color_variation_images", joinColumns = @JoinColumn(name = "color_variation_id"))
     @Column(name = "image_url")
     private List<String> images = new ArrayList<>();

     @OneToMany(mappedBy = "colorVariation" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
     private List<SizeVariation> sizeVariations = new ArrayList<>();

     @OneToMany(mappedBy = "colorVariation" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
     private List<WishListItem> wishListItems = new ArrayList<>();

}
