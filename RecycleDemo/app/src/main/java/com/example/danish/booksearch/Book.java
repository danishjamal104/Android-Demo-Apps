package com.example.danish.booksearch;

/**
 * Created by danish on 27/3/18.
 */

public class Book {
    private String mTitle;
    private String mPages;
    private String mAuthor;
    private String mBuyUrl;
    private String mImageLink;

    public Book(String title, String pages, String author, String buyUrl, String imageLink){
        mTitle = title;
        mPages = pages;
        mAuthor = author;
        mBuyUrl = buyUrl;
        mImageLink = imageLink;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmPages() {
        return mPages;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmBuyUrl() {
        return mBuyUrl;
    }

    public String getmImageLink(){
        return mImageLink;
    }
}
