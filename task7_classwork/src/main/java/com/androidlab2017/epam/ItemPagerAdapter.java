package com.androidlab2017.epam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by roman on 19.5.17.
 */

public class ItemPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Item> mItems;

    public ItemPagerAdapter(FragmentManager fm, ArrayList<Item> items) {
        super(fm);
        this.mItems = items;
    }

    @Override
    public Fragment getItem(int position) {
        return ItemPageFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }
}
