package com.mroueh.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="shopping_cart_item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

    private String productName;

    private String image;

    private String color;

    @ManyToOne
    @JoinColumn(name = "size_variation_id")
    private SizeVariation sizeVariation;

    @Positive
    private int quantity;

    private double unitPrice;

}
