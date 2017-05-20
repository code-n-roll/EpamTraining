package com.androidlab2017.epam.tasks.task2;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidlab2017.epam.lab001.R;

import java.util.Random;

/**
 * Created by roman on 8.5.17.
 */

public class ColorButtonsFragment extends Fragment {
    private View mView;
    private Button mButtonFirstColor,
            mButtonSecondColor,
            mButtonThirdColor;
    private final static String FIRST_COLOR_TAG = "FIRST_COLOR",
            SECOND_COLOR_TAG = "SECOND_COLOR",
            THIRD_COLOR_TAG = "THIRD_COLOR";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_color_buttons,container,false);
        return mView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(FIRST_COLOR_TAG, ((ColorDrawable)mButtonFirstColor.getBackground()).getColor());
        outState.putInt(SECOND_COLOR_TAG, ((ColorDrawable)mButtonSecondColor.getBackground()).getColor());
        outState.putInt(THIRD_COLOR_TAG, ((ColorDrawable)mButtonThirdColor.getBackground()).getColor());
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity)getActivity();

            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

            mButtonFirstColor = (Button) activity.findViewById(R.id.button_first_color);
            mButtonSecondColor = (Button) activity.findViewById(R.id.button_second_color);
            mButtonThirdColor = (Button) activity.findViewById(R.id.button_third_color);

            if (savedInstanceState != null) {
                mButtonFirstColor.setBackgroundColor(savedInstanceState.getInt(FIRST_COLOR_TAG));
                mButtonSecondColor.setBackgroundColor(savedInstanceState.getInt(SECOND_COLOR_TAG));
                mButtonThirdColor.setBackgroundColor(savedInstanceState.getInt(THIRD_COLOR_TAG));
            } else {
                mButtonFirstColor.setBackgroundColor(ContextCompat.getColor(getContext(),
                        android.R.color.darker_gray));
                mButtonSecondColor.setBackgroundColor(ContextCompat.getColor(getContext(),
                        android.R.color.darker_gray));
                mButtonThirdColor.setBackgroundColor(ContextCompat.getColor(getContext(),
                        android.R.color.darker_gray));
            }

            mButtonFirstColor.setOnClickListener((ignored)->clickOnButtonFirstColor());
            mButtonSecondColor.setOnClickListener((ignored)->clickOnButtonSecondColor());
            mButtonThirdColor.setOnClickListener((ignored)->clickOnButtonThirdColor());
        }
    }


    private void clickOnButtonFirstColor(){
        mButtonFirstColor.setBackgroundColor(getRandomColor());
    }

    private void clickOnButtonSecondColor(){
        mButtonSecondColor.setBackgroundColor(getRandomColor());
    }

    private void clickOnButtonThirdColor(){
        mButtonThirdColor.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor(){
        Random rnd = new Random();
        int color;
        int choose = rnd.nextInt(100);
        if (choose % 2 == 0) {
            color = Color.argb(255,
                    rnd.nextInt(2) * 100, rnd.nextInt(2) * 100, rnd.nextInt(2) * 100);
        } else if (choose % 3 == 0){
            color = Color.argb(255, 50, 50, 150);
        } else {
            color = Color.argb(255, 50, 150, 50);
        }
        return color;
    }
}
