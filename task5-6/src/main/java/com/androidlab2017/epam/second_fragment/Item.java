package com.androidlab2017.epam.second_fragment;

/**
 * Created by roman on 17.5.17.
 */

public class Item {
    private final String mContent;
    private final String mImageURL;

    public Item(String content, String imageURL){
        mContent = content;
        mImageURL = imageURL;
    }

    public String getContent() {
        return mContent;
    }

    public String getImageURL() {
        return mImageURL;
    }
}
