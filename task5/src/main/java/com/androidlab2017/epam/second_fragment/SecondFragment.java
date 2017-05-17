package com.androidlab2017.epam.second_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidlab2017.epam.R;

import java.util.ArrayList;

/**
 * Created by roman on 12.5.17.
 */

public class SecondFragment extends Fragment {
    private View mView;
    private ItemListAdapter mItemListAdapter;
    private ArrayList<Item> mItems;
    private RecyclerView mItemListRecycler;
    private RecyclerView.LayoutManager mLayoutManager;
    private final static String imageURL =
            "https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_second, container, false);

        mItemListRecycler = (RecyclerView) mView.findViewById(R.id.custom_items_recycler_view);

        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mItems.add(new Item("some interesting content",imageURL));
        }

        mItemListAdapter = new ItemListAdapter(mItems);

        mLayoutManager = new LinearLayoutManager(getContext());
        mItemListRecycler.setLayoutManager(mLayoutManager);

        mItemListRecycler.setAdapter(mItemListAdapter);
        mItemListRecycler.setLayoutManager(mLayoutManager);
        return mView;
    }
}
