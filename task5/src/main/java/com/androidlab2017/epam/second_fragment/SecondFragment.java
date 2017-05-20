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
    private final static String IMAGE_URL =
            "https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg";

    private ArrayList<Item> mItems;
    private RecyclerView mItemListRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViewsById(view);
        createItemListContent();
        initItemListRecycler();
    }

    private void findViewsById(View view){
        mItemListRecycler = (RecyclerView) view.findViewById(R.id.custom_items_recycler_view);
    }

    private void initItemListRecycler(){
        final ItemListAdapter itemListAdapter = new ItemListAdapter(mItems);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mItemListRecycler.setLayoutManager(layoutManager);
        mItemListRecycler.setAdapter(itemListAdapter);
        mItemListRecycler.setLayoutManager(layoutManager);
    }

    private void createItemListContent(){
        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mItems.add(new Item("some interesting content", IMAGE_URL));
        }
    }
}
