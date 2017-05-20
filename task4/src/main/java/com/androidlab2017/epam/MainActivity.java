package com.androidlab2017.epam;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidlab2017.epam.task5.R;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity{
    private ImageButton mButtonCotrolAnimation;
    private TextView mTextViewContent;

    private static final String ID_STATE_TAG = MainActivity.class.getName()+"_COUNTER_TAG";
    private static final String OLD_GRAVITY_TAG = MainActivity.class.getName()+"_OLD_GRAVITY";
    private static final int FROM_END_BOTTOM = 3;
    private int mIdState = 0;
    private int mCurGravity;

    private static int[] gravityStates = {
            Gravity.START | Gravity.TOP,
            Gravity.END | Gravity.TOP,
            Gravity.END | Gravity.BOTTOM,
            Gravity.START | Gravity.BOTTOM
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mButtonCotrolAnimation = (ImageButton) findViewById(R.id.id_imagebutton_control_anim);
        mTextViewContent = (TextView) findViewById(R.id.id_textview_content);


        if (savedInstanceState != null){
            changeGravityOnView(mTextViewContent, savedInstanceState.getInt(OLD_GRAVITY_TAG));
            mIdState = savedInstanceState.getInt(ID_STATE_TAG);
            mCurGravity = gravityStates[mIdState];
        }

        Picasso.with(this).load("https://galagram.com/wp-content/uploads/2017/03/" +
                "android-8-logo-png-transparent-logo-of-android-8.png").into(mButtonCotrolAnimation);

        mButtonCotrolAnimation.setOnLongClickListener((ignored)-> controlGravityListener());
    }

    private void changeGravityOnView(TextView textView, int newGravity){
        FrameLayout.LayoutParams lp = ((FrameLayout.LayoutParams) textView.getLayoutParams());
        lp.gravity = newGravity;
        textView.setLayoutParams(lp);
    }

    private void changeGravityValue(){
        if (mIdState != FROM_END_BOTTOM){
            mIdState++;
        } else {
            mIdState = 0;
        }
        mCurGravity = gravityStates[mIdState];
    }

    private boolean controlGravityListener(){
        changeGravityValue();
        changeGravityOnView(mTextViewContent, mCurGravity);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(OLD_GRAVITY_TAG, mCurGravity);
        outState.putInt(ID_STATE_TAG, mIdState);
    }
}
