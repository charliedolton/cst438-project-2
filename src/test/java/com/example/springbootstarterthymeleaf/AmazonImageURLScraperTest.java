package com.example.springbootstarterthymeleaf;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static com.example.springbootstarterthymeleaf.database.AmazonImageURLScraper.getAmazonImageURL;

public class AmazonImageURLScraperTest {

    @Test
    void TestSimpleItem() throws IOException {
        String AmazonURL = "https://www.amazon.com/Pepe-Frog-official-anatomically-correct/dp/B01MCT549R/";
        String expectedImageURL = "https://images-na.ssl-images-amazon.com/images/I/61dnV42UYaL.__AC_SX300_SY300_QL70_ML2_.jpg";

        String ImageURL = getAmazonImageURL(AmazonURL);

        System.out.println(ImageURL);

        assert (expectedImageURL.equals(ImageURL));
    }

    @Test
    void TestBookItem() throws IOException {
        String AmazonURL = "https://www.amazon.com/Sonichu-0-C-W/dp/1699276854/";
        String expectedImageURL = "https://images-na.ssl-images-amazon.com/images/I/51F9RP7npiL._SX218_BO1,204,203,200_QL40_ML2_.jpg";

        String ImageURL = getAmazonImageURL(AmazonURL);

        System.out.println(ImageURL);

        assert (expectedImageURL.equals(ImageURL));
    }
}

