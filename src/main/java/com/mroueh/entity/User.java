package com.mroueh.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    @NotBlank(message = "Username is mandatory")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user")
    private ShoppingCart cart;

    @OneToMany
    private List<Address> address = new ArrayList<>();

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<UserReview> review;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<WishListItem>  wishListItems;

}
