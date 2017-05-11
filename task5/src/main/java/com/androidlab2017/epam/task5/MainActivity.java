package com.androidlab2017.epam.task5;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity{
    private ImageButton mButtonCotrolAnimation;
    private TextView mTextViewContent;
    private Animation mToRightAnimation;
    private Animation mToLeftAnimation;
    private Animation mToEndAnimation;
    private Animation mToStartAnimation;
    private int counter = 0;
    private static final int FROM_LEFT = 0,
                            FROM_START = 1,
                            FROM_RIGHT = 2,
                            FROM_END = 3;
    private static final String XTAG = "XTAG",
                                YTAG = "YTAG",
                                CONTER_TAG = "COUNTER_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToRightAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_to_right);
        mToLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_to_left);
        mToStartAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_to_start);
        mToEndAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_to_end);


        mToRightAnimation.setFillAfter(true);
        mToLeftAnimation.setFillAfter(true);
        mToStartAnimation.setFillAfter(true);
        mToEndAnimation.setFillAfter(true);



        mToRightAnimation.setAnimationListener(mFixPositionListener);


        mButtonCotrolAnimation = (ImageButton) findViewById(R.id.id_imagebutton_control_anim);
        mTextViewContent = (TextView) findViewById(R.id.id_textview_content);

        if (savedInstanceState != null){
            mTextViewContent.setX(savedInstanceState.getFloat(XTAG));
            mTextViewContent.setY(savedInstanceState.getFloat(YTAG));
            counter = savedInstanceState.getInt(CONTER_TAG);
        }

        Picasso.with(this).load("https://galagram.com/wp-content/uploads/2017/03/" +
                "android-8-logo-png-transparent-logo-of-android-8.png").into(mButtonCotrolAnimation);

        mButtonCotrolAnimation.setOnLongClickListener((ignored)->longClickOnButtonControlAnimation());

    }

    private Animation.AnimationListener mFixPositionListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
//            mTextViewContent.layout(100,0,
//                    mTextViewContent.getWidth(),
//                    mTextViewContent.getHeight());
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private boolean longClickOnButtonControlAnimation(){
        switch (counter){
            case FROM_LEFT:
                mTextViewContent.startAnimation(mToRightAnimation);
                counter++;
                break;
            case FROM_START:
                mTextViewContent.startAnimation(mToEndAnimation);
                counter++;
                break;
            case FROM_RIGHT:
                mTextViewContent.startAnimation(mToLeftAnimation);
                counter++;
                break;
            case FROM_END:
                mTextViewContent.startAnimation(mToStartAnimation);
                counter = 0;
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putFloat(XTAG, mTextViewContent.getX());
        outState.putFloat(YTAG, mTextViewContent.getY());
        outState.putInt(CONTER_TAG, counter);
    }
}
