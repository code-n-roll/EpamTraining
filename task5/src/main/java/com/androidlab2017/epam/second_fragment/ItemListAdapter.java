package com.androidlab2017.epam.second_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidlab2017.epam.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by roman on 17.5.17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder>{
    private final ArrayList<Item> mItems;

    public ItemListAdapter(ArrayList<Item> items) {
        mItems = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.content_item_cardview, parent, false
        );
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImageItem;
        private TextView mContentItem;
        private View mItemView;

        public ViewHolder(View itemView) {
            super(itemView);

            mItemView = itemView;
            mImageItem = (ImageView) itemView.findViewById(R.id.image_item_imageview);
            mContentItem = (TextView) itemView.findViewById(R.id.content_item_textview);
        }

        public void bind(final Item item){
            mContentItem.setText(item.getContent());
            Picasso.with(mItemView.getContext()).load(item.getImageURL()).into(mImageItem);
        }
    }
}
