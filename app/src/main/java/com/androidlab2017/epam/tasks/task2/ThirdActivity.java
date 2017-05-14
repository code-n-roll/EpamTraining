package com.androidlab2017.epam.tasks.task2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.androidlab2017.epam.lab001.R;

/**
 * Created by roman on 5.5.17.
 */

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivityTag";
    private Button mCloseThirdActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mCloseThirdActivity = (Button) findViewById(R.id.button_close_third_activity);
        mCloseThirdActivity.setOnClickListener((ignored)->clickOnCloseThirdActivity());

        Log.d(TAG, "onCreate");
    }

    private void clickOnCloseThirdActivity(){
        finish();
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
}
