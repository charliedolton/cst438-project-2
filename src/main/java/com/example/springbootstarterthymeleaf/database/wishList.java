package com.example.springbootstarterthymeleaf.database;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

import java.util.ArrayList;

@Entity
public class wishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer wishListId; 

    private String Listname; 

    private ArrayList<item> mWishList;  

    private Integer userId; 

    
    
}
