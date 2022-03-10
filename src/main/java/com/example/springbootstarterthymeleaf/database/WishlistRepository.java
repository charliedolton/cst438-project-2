package com.example.springbootstarterthymeleaf.database;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface WishlistRepository extends CrudRepository<WishList, Integer> {

    WishList findWishListByWishListId(Integer wishlistId);
    WishList findWishListByUserId(Integer userId);
}
