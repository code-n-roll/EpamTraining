package com.androidlab2017.epam.tasks;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.androidlab2017.epam.lab001.R;
import com.androidlab2017.epam.tasks.task2.MainFragment;
import com.androidlab2017.epam.tasks.task3.FirstFragment;
import com.androidlab2017.epam.tasks.task3.OnChangeBackgroundListener;
import com.androidlab2017.epam.tasks.task3.OnChangeFragmentListener;
import com.androidlab2017.epam.tasks.task3.SecondFragment;
import com.androidlab2017.epam.tasks.task3.ThirdFragment;

import java.util.Random;



public class MainActivity extends AppCompatActivity implements
        OnChangeBackgroundListener, OnChangeFragmentListener {
    private final static String TAG = "MainActivityTag";
    private final static String CUR_FRAGMENT_TAG = "CUR_FRAGMENT_TAG",
                        COLOR_SECOND_TAG = "COLOR_SECOND_TAG",
                        COLOR_THIRD_TAG = "COLOR_THIRD_TAG";
    private String mCurFragmentTag;
    private Random mRandom;
    private Fragment mSecondFragment;
    private Fragment mThirdFragment;
    private int mColorSecond;
    private int mColorThird;

    public int getColorSecond() {
        return mColorSecond;
    }

    public int getColorThird() {
        return mColorThird;
    }


    public void setCurFragmentTag(final String curFragmentTag){
        mCurFragmentTag = curFragmentTag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandom = new Random();

        mSecondFragment = new SecondFragment();
        mThirdFragment = new ThirdFragment();

        if (savedInstanceState != null){
            setCurFragmentTag(savedInstanceState.getString(CUR_FRAGMENT_TAG));
            mColorSecond = savedInstanceState.getInt(COLOR_SECOND_TAG);
            mColorThird = savedInstanceState.getInt(COLOR_THIRD_TAG);
        } else {
            getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.id_start_sub_main_container, new FirstFragment(), FirstFragment.class.getName()).
                    add(R.id.id_end_sub_main_container, mSecondFragment, SecondFragment.class.getName()).
                    addToBackStack(SecondFragment.class.getName()).
                    commit();
            setCurFragmentTag(SecondFragment.class.getName());
        }

        Log.d(TAG,"onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CUR_FRAGMENT_TAG, mCurFragmentTag);
        outState.putInt(COLOR_SECOND_TAG, mColorSecond);
        outState.putInt(COLOR_THIRD_TAG, mColorThird);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            if (!mCurFragmentTag.equals(MainFragment.class.getName())){
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.main_container, new MainFragment(), MainFragment.class.getName()).
                        commit();
                setCurFragmentTag(MainFragment.class.getName());
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }


    @Override
    public void onChangeFragment() {
        if (mCurFragmentTag.equals(SecondFragment.class.getName())){
            mSecondFragment = getSupportFragmentManager().findFragmentByTag(mCurFragmentTag);
            changeToFragment(mThirdFragment, ThirdFragment.class.getName());
            setCurFragmentTag(ThirdFragment.class.getName());
        } else {
            mThirdFragment = getSupportFragmentManager().findFragmentByTag(mCurFragmentTag);
            changeToFragment(mSecondFragment, SecondFragment.class.getName());
            setCurFragmentTag(SecondFragment.class.getName());
        }
    }

    private void changeToFragment(Fragment fragment, String fragmentTag){
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.id_end_sub_main_container, fragment, fragmentTag).
                addToBackStack(fragmentTag).
                commit();
    }

    @Override
    public void onChangeBackground() {
        Fragment curFragment = getSupportFragmentManager().findFragmentByTag(mCurFragmentTag);
        int color = 0;
        if (curFragment != null && curFragment.getView() != null){
            color = Color.rgb(mRandom.nextInt(255),
                    mRandom.nextInt(255),
                    mRandom.nextInt(255));
            curFragment.getView().setBackgroundColor(color);
        }
        if (curFragment instanceof SecondFragment){
            mColorSecond = color;
        } else if (curFragment instanceof ThirdFragment){
            mColorThird = color;
        }
    }
}
