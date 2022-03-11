package com.example.springbootstarterthymeleaf.database;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    private String username;

    private String firstName;

    private String lastName;

    private String pictureURL;

    private String password;

    @OneToMany
    private List<WishList> wishlists;

    public User() {

    }
    public User(String username, String firstName, String lastName, String pictureURL, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pictureURL = pictureURL;
        this.password = password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return userId.equals(user.userId);
//    }


    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pictureURL='" + pictureURL + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WishList> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<WishList> wishlists) {
        this.wishlists = wishlists;
    }
}
