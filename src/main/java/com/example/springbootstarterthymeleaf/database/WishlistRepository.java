package com.example.springbootstarterthymeleaf.database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface WishlistRepository extends CrudRepository<WishList, Integer> {

    WishList findWishListByWishListId(Integer wishlistId);
    WishList findWishListByUserId(Integer userId);

    @Query(value = "SELECT itemList FROM Wishlist w WHERE w.wishlistId = :wishlistId",
            countQuery = "SELECT count(*) FROM Wishlist",
            nativeQuery = true)
    List<Item> getItemList(
            @Param("wishlistId") Integer wishlistId
    );
}
