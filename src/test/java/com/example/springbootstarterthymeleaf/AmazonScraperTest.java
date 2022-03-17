package com.example.springbootstarterthymeleaf;

import com.example.springbootstarterthymeleaf.database.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static com.example.springbootstarterthymeleaf.database.AmazonScraper.*;

public class AmazonScraperTest {

    @Test
    void makeItemTest() {
        String AmazonURL = "https://www.amazon.com/Pepe-Frog-official-anatomically-correct/dp/B01MCT549R/";

        Item expectedItem = new Item();

        expectedItem.setItemName("Hashtag Collectibles Pepe The Frog - The Official Plush Doll (Anatomically Correct)");
        expectedItem.setItemPictureURL("https://images-na.ssl-images-amazon.com/images/I/61dnV42UYaL.__AC_SX300_SY300_QL70_ML2_.jpg");
        expectedItem.setAmazonURL(AmazonURL);
        expectedItem.setItemPrice(34); // this price can not be guaranteed to be right

        Item testItem = makeItem(AmazonURL);

        assert(expectedItem.getItemName().equals(Objects.requireNonNull(testItem).getItemName()));
        assert (expectedItem.getAmazonURL().equals(testItem.getAmazonURL()));
        assert (expectedItem.getItemPictureURL().equals(testItem.getItemPictureURL()));
        assert (expectedItem.getItemPrice().equals(testItem.getItemPrice()));
    }

    @Test
    void makeBookItemTest() {
        String AmazonURL = "https://www.amazon.com/Sonichu-0-C-W/dp/1699276854/";

        Item expectedItem = new Item();

        expectedItem.setItemName("Sonichu #0");
        expectedItem.setItemPictureURL("https://images-na.ssl-images-amazon.com/images/I/51F9RP7npiL._SX218_BO1,204,203,200_QL40_ML2_.jpg");
        expectedItem.setAmazonURL(AmazonURL);
        expectedItem.setItemPrice(10); // this price can not be guaranteed to be right

        Item testItem = makeItem(AmazonURL);

        assert(expectedItem.getItemName().equals(Objects.requireNonNull(testItem).getItemName()));
        assert (expectedItem.getAmazonURL().equals(testItem.getAmazonURL()));
        assert (expectedItem.getItemPictureURL().equals(testItem.getItemPictureURL()));
        assert (expectedItem.getItemPrice().equals(testItem.getItemPrice()));
    }

    @Test
    void badURLTest() {
        String AmazonURL = "https://www.amazon.com/bad_link_1231231";
        Item testItem = makeItem(AmazonURL);
        assert (testItem == null);
    }

    @Test
    void getImageURLTest () {
        String AmazonURL = "https://www.amazon.com/Pepe-Frog-official-anatomically-correct/dp/B01MCT549R/";
        String expectedImageURL = "https://images-na.ssl-images-amazon.com/images/I/61dnV42UYaL.__AC_SX300_SY300_QL70_ML2_.jpg";

        String ImageURL = getImageURL(AmazonURL);

        assert (expectedImageURL.equals(ImageURL));
    }

    @Test
    void getBookImageURLTest() {
        String AmazonURL = "https://www.amazon.com/Sonichu-0-C-W/dp/1699276854/";
        String expectedImageURL = "https://images-na.ssl-images-amazon.com/images/I/51F9RP7npiL._SX218_BO1,204,203,200_QL40_ML2_.jpg";

        String ImageURL = getImageURL(AmazonURL);

        assert (expectedImageURL.equals(ImageURL));
    }
}
