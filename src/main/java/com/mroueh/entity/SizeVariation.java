package com.mroueh.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="size_Variation")
@Builder
public class SizeVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qty_in_stock")
    private int quantityInStock;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeOption sizeOption;

    @ManyToOne
    @JoinColumn(name = "color_variation_id")
    private ColorVariation colorVariation;

    @OneToMany(mappedBy = "sizeVariation" , cascade = CascadeType.ALL)
    private List<ShoppingCartItem> shoppingCartItem = new ArrayList<>();

    @Column(name = "product_code")
    private String code;

    @OneToMany(mappedBy = "sizeVariation" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<WishListItem> wishListItems = new ArrayList<>();

}
