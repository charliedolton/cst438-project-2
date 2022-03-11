package com.example.springbootstarterthymeleaf.database;


import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wishListId; 

    private String listName;

    @OneToMany
    private List<Item> items;

    private Integer userId;

    public Integer getWishListId() {
        return wishListId;
    }

    public void setWishListId(Integer wishListId) {
        this.wishListId = wishListId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
