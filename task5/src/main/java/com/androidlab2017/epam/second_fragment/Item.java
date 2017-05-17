package com.androidlab2017.epam.second_fragment;

/**
 * Created by roman on 17.5.17.
 */

public class Item {
    private String mContent;
    private String mImageURL;

    public Item(String content, String imageURL){
        mContent = content;
        mImageURL = imageURL;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }
}
