package com.mroueh.repository;

import com.mroueh.entity.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem , Long> {

    WishListItem findByColorVariationId(Long id);
    WishListItem findBySizeVariationId(Long id);

//    void deleteById(Long id);
}
