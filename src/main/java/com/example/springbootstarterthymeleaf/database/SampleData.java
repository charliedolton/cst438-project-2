package com.example.springbootstarterthymeleaf.database;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public User getSampleUser() {
        SampleDataSingleton dataSingleton = SampleDataSingleton.getInstance();
        return SampleData.SampleDataSingleton.getInstance().sampleUser;
    }

    public Item getSampleItem() {
        return SampleDataSingleton.getInstance().simpleItem;
    }

    public Item getSampleBookItem() {
        return SampleDataSingleton.getInstance().sampleBookItem;
    }

    public List<Item> getSampleItemList() {
        return SampleDataSingleton.getInstance().sampleItemList;
    }

    public WishList getSampleWishList() {
        return SampleDataSingleton.getInstance().wishList1;
    }

    public WishList getEmptyWishlist() {
        return SampleDataSingleton.getInstance().emptyWishList;
    }

    private static class SampleDataSingleton {
        private static SampleDataSingleton instance = null;
        public User sampleUser;
        public Item simpleItem;
        public Item sampleBookItem;
        public List<Item> sampleItemList;
        public WishList wishList1;
        public WishList emptyWishList;

        private SampleDataSingleton() {
            sampleUser = new User("JaneDoe", "Jane", "Doe", "https://i.imgur.com/nTxJseA.jpg", "pass");
            sampleUser.setUserId(11111);

            simpleItem = new Item();
            simpleItem.setItemId(9999);
            simpleItem.setItemName("Pepe the Frog Plush Doll");
            simpleItem.setItemPrice(34);
            simpleItem.setItemPictureURL("https://images-na.ssl-images-amazon.com/images/I/61dnV42UYaL.__AC_SX300_SY300_QL70_ML2_.jpg");
            simpleItem.setAmazonURL("https://www.amazon.com/Pepe-Frog-official-anatomically-correct/dp/B01MCT549R/");

            sampleBookItem = new Item();
            sampleBookItem.setItemId(1337);
            sampleBookItem.setItemPrice(20);
            sampleBookItem.setItemName("Sonichu Issue 0");
            sampleBookItem.setAmazonURL("https://www.amazon.com/Sonichu-0-C-W/dp/1699276854/");
            sampleBookItem.setItemPictureURL("https://images-na.ssl-images-amazon.com/images/I/51F9RP7npiL._SX218_BO1,204,203,200_QL40_ML2_.jpg");

            sampleItemList = new ArrayList<>();
            sampleItemList.add(simpleItem);
            sampleItemList.add(sampleBookItem);

            wishList1 = new WishList();
            wishList1.setWishListId(1234);
            wishList1.setListName("Sample Wishlist");
            wishList1.setUserId(sampleUser.getUserId());
            wishList1.setItems(sampleItemList);

            emptyWishList = new WishList();
            emptyWishList.setWishListId(12345);
            emptyWishList.setListName("Empty Wishlist");
            emptyWishList.setUserId(sampleUser.getUserId());
            emptyWishList.setItems(new ArrayList<>());

            sampleUser.setWishlists(List.of(wishList1, emptyWishList));
        }

        public static SampleDataSingleton getInstance() {
            if (instance == null) {
                instance = new SampleDataSingleton();
            }

            return instance;
        }
    }

}
