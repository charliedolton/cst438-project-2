package com.example.springbootstarterthymeleaf;


import com.example.springbootstarterthymeleaf.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class Api {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ItemRepository itemRepository;

    //User API endpoints
    @RequestMapping("/getAllUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping("/getUserByUsername")
    public @ResponseBody User getUserByUsername(@RequestParam(defaultValue = "user") String name) {
        return userRepository.findByUsername(name);
    }

    @RequestMapping("/getUserByUserId")
    public @ResponseBody User getUserByUserId(@RequestParam(defaultValue = "0") Integer userId){
        return userRepository.findByUserId(userId);
    }

    //Wishlist API endpoints
    @RequestMapping("/getWishlistByUserId")
    public @ResponseBody WishList getWishlistByUserId(@RequestParam(defaultValue = "0") Integer userId){
        return wishlistRepository.findWishListByUserId(userId);
    }

    @RequestMapping("/getWishlistByWishlistId")
    public @ResponseBody WishList getWishlistByWishlistId(@RequestParam(defaultValue = "0") Integer wishlistId){
        return wishlistRepository.findWishListByWishListId(wishlistId);
    }

    //Item API endpoints
    @RequestMapping("/getItemByItemId")
    public @ResponseBody Item getItemByItemId(@RequestParam(defaultValue = "0") Integer itemId){
        return itemRepository.findItemByItemId(itemId);
    }

    @RequestMapping("/usernameIsTaken")
    public @ResponseBody Boolean usernameIsTaken(@RequestParam(defaultValue = "user") String username) {
        return userRepository.existsUserByUsername(username);
    }
}
