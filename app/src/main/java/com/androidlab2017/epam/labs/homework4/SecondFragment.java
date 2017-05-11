package com.androidlab2017.epam.labs.homework4;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidlab2017.epam.lab001.R;
import com.androidlab2017.epam.labs.MainActivity;

/**
 * Created by roman on 10.5.17.
 */

public class SecondFragment extends Fragment {
    private View mView;
    private final static String VIEW_BACKGROUND_COLOR = "BACKGROUND_COLOR";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_second,container,false);

        mView.setBackgroundColor(((MainActivity)getActivity()).getColorSecond());
        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        int color = ((ColorDrawable)mView.getBackground()).getColor();
        outState.putInt(VIEW_BACKGROUND_COLOR, color);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mView.setBackgroundColor(savedInstanceState.getInt(VIEW_BACKGROUND_COLOR));
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
