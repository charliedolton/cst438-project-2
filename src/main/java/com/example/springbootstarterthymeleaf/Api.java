package com.example.springbootstarterthymeleaf;


import com.example.springbootstarterthymeleaf.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        return userRepository.findUserByUsername(name);
    }

    @RequestMapping("/getUserByUserId")
    public @ResponseBody User getUserByUserId(@RequestParam(defaultValue = "0") Integer userId){
        return userRepository.findUserByUserId(userId);
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

    @PostMapping("/addItemToWishlist")
    public String addItemToWishlist(@RequestParam Integer wishlistId,
                                    @RequestParam String itemName,
                                    @RequestParam Integer itemPrice,
                                    @RequestParam String amazonURL,
                                    @RequestParam String imageURL) {
        WishList wishList = wishlistRepository.findWishListByWishListId(wishlistId);
        Item item = new Item(amazonURL, itemName, itemPrice, imageURL);
        itemRepository.save(item);
        List<Item> itemList = wishList.getItems();
        itemList.add(item);
        wishList.setItems(itemList);

        String url = "redirect:/addItemToWishlist?wishlistId=" + wishlistId;
        return url;
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestParam Integer wishlistId,
                             @RequestParam Integer itemId) {
        WishList wishList = wishlistRepository.findWishListByWishListId(wishlistId);
        List<Item> itemList = wishList.getItems();
        Item item = itemRepository.findItemByItemId(itemId);
        itemList.remove(item);
        itemRepository.delete(item);
        wishList.setItems(itemList);

        return "redirect:/editWishlist";
    }

    @PostMapping("/createWishlist")
    public String createWishlist(@RequestParam String wishlistName,
                                 @RequestParam Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        WishList wishlist = new WishList(wishlistName, userId);
        wishlistRepository.save(wishlist);
        List<WishList> wishlists = user.getWishlists();
        wishlists.add(wishlist);
        user.setWishlists(wishlists);

        return "redirect:/homePage";
    }
}
