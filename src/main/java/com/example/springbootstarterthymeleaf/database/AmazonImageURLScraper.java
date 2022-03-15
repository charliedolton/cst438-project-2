package com.example.springbootstarterthymeleaf.database;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class AmazonImageURLScraper {
    public static String getAmazonImageURL(String AmazonItemURL) throws IOException {
        Document page = Jsoup.connect(AmazonItemURL).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:80.0) Gecko/27132701 Firefox/78.7").data("name", "jsoup").get();
        Element mainImageElement = page.selectFirst("#main-image-container"); // get the main image container
        String URL = mainImageElement.selectFirst("img[src$=.jpg]").attr("src"); // find the fist jpg within the main image element
        return URL;
    }
}