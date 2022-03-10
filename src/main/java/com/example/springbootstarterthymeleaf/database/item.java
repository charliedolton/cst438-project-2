package com.example.springbootstarterthymeleaf.database;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class item {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId; 

    private String amazonURL; 

    private String itemName; 
    
    private Integer itemPrice; 
    
    private String itemPictureURL; 
    
    
}
