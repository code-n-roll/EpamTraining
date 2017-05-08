package com.androidlab2017.epam.task1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.androidlab2017.epam.lab001.R;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivityTag",
            MAIN_FRAGMENT = "MainFragment";
    private final static String CUR_FRAGMENT_TAG = "CUR_FRAGMENT_TAG";
    private String mCurFragmentTag;

    public void setCurFragmentTag(final String curFragmentTag){
        mCurFragmentTag = curFragmentTag;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            setCurFragmentTag(savedInstanceState.getString(CUR_FRAGMENT_TAG));
        } else {
            setContentView(R.layout.activity_main);
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.main_container, new MainFragment(), MAIN_FRAGMENT).
                    commit();
            setCurFragmentTag(MAIN_FRAGMENT);
        }

        Log.d(TAG,"onCreate");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(
                outState,
                mCurFragmentTag,
                getSupportFragmentManager().findFragmentByTag(mCurFragmentTag)
        );
        outState.putString(CUR_FRAGMENT_TAG, mCurFragmentTag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            if (!mCurFragmentTag.equals(MAIN_FRAGMENT)){
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.main_container, new MainFragment(), MAIN_FRAGMENT).
                        commit();
                setCurFragmentTag(MAIN_FRAGMENT);
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


}
