package com.androidlab2017.epam;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mPagerItems;
    private ItemPagerAdapter mItemPagerAdapter;
    private ArrayList<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            mItems.add(new Item());
        }

        mPagerItems = (ViewPager) findViewById(R.id.pagerItem);
        mItemPagerAdapter = new ItemPagerAdapter(getSupportFragmentManager(),mItems);
        mPagerItems.setAdapter(mItemPagerAdapter);

    }
}
