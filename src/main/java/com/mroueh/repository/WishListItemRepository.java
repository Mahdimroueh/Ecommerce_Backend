package com.mroueh.repository;

import com.mroueh.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListItemRepository extends JpaRepository<WishListItem , Long> {

}
