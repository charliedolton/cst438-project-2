package com.example.springbootstarterthymeleaf.database;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class AmazonScraper {
    /**
     * @param AmazonURL a valid Amazon URL
     * @return An image URL.
     *      Returns null if an error is encountered
     */
    public static String getImageURL(String AmazonURL) {
        Document page;
        try {
            page = Jsoup.connect(AmazonURL).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:80.0) Gecko/27132701 Firefox/78.7").data("name", "jsoup").get();
        } catch (IOException e) {
            return null; // error encountered
        }

        Element mainImageElement = page.selectFirst("#main-image-container"); // get the main image container
        String URL = mainImageElement.selectFirst("img[src$=.jpg]").attr("src"); // find the fist jpg within the main image element
        return URL;
    }

    /**
     * @param AmazonURL a valid Amazon URL
     * @return An Item created by scraping an Amazon web page.
     *      Returns null if an error is encountered
     */
    public static Item makeItem(String AmazonURL) {
        Document page;
        try {
            page = Jsoup.connect(AmazonURL).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:80.0) Gecko/27132701 Firefox/78.7").data("name", "jsoup").get();
        } catch (IOException e) {
            return null; // error encountered
        }

        Item item = new Item();

        item.setAmazonURL(AmazonURL);
        item.setItemName(page.selectFirst("#productTitle").html());
        item.setItemPictureURL(page.selectFirst("#main-image-container").selectFirst("img[src$=.jpg]").attr("src"));

        Element container = page.selectFirst("div.a-box-group");
        Element priceContainer = container.selectFirst("#price");
        String priceString;

        if (priceContainer != null) {
            priceString = priceContainer.ownText();
            // get return a float instead of an int
            // priceString = priceString.substring(1);
            priceString = priceString.substring(1, priceString.indexOf('.', 1));
        } else { // element was not found in page
            priceString = container.selectFirst("span.a-price-whole").ownText(); // only gets text directly within span (ignore nested text)
            // get return a float instead of an int.
            // priceString = container.selectFirst("span.a-price-whole").text(); // gets all text within this span (gets an extra decimal point in this case)
            // priceString += container.selectFirst("span.a-price-fraction").ownText(); // add value of cents to price
        }

        item.setItemPrice(Integer.parseInt(priceString));
        // item.setItemPrice(Float.parseFloat(priceString));

        return item;
    }
}
