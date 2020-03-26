package com.example.progandro;

public class MovieRecyclerItem {
    private int mImageResource;
    private String mTitle;
    private String mDescription;

    public MovieRecyclerItem(int imageResource, String title, String description) {
        mImageResource = imageResource;
        mTitle = title;
        mDescription = description;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }
}
