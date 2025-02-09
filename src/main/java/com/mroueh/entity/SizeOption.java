package com.mroueh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="size_option")
public class SizeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size_name")
    private String name;

    @Column(name = "sort_order")
    private String sortOrder;

    @ManyToOne
    @JoinColumn(name = "size_category_id")
    private SizeCategory sizeCategory;
}
