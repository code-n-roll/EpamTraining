package com.androidlab2017.epam.tasks.task2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.androidlab2017.epam.lab001.R;

/**
 * Created by roman on 5.5.17.
 */

public class SecondActivity extends AppCompatActivity{
    private static final String TAG = "SecondActivityTag";
    private Button mToThirdActivityButton;
    private Button mCloseSecondActivity;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        mToThirdActivityButton = (Button) findViewById(R.id.button_to_third_activity);
        mToThirdActivityButton.setOnClickListener((ignored)->clickToSecondActivityButton());

        mCloseSecondActivity = (Button) findViewById(R.id.button_close_second_activity);
        mCloseSecondActivity.setOnClickListener((ignored)->clickOnCloseSecondActivity());

        Log.d(TAG, "onCreate");
    }

    private void clickOnCloseSecondActivity(){
        finish();
    }

    private void clickToSecondActivityButton(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
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
