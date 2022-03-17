package com.example.springbootstarterthymeleaf.database;



import javax.persistence.*;

@Entity
public class Item {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemId;

    private String amazonURL; 

    private String itemName;

    private Integer itemPrice;
    
    private String itemPictureURL;

    public Item(){

    }

    public Item(String amazonURL, String itemName, Integer itemPrice, String itemPictureURL) {
        this.amazonURL = amazonURL;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemPictureURL = itemPictureURL;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAmazonURL() {
        return amazonURL;
    }

    public void setAmazonURL(String amazonURL) {
        this.amazonURL = amazonURL;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemPictureURL() {
        return itemPictureURL;
    }

    public void setItemPictureURL(String itemPictureURL) {
        this.itemPictureURL = itemPictureURL;
    }
}
