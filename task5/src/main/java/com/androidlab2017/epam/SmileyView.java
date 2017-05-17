package com.androidlab2017.epam;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by roman on 14.5.17.
 */

public class SmileyView extends View {
    private Paint mFacePaint;
    private Paint mEyesPaint;
    private Paint mMouthPaint;

    private int mEyesColor;
    private int mMouthColor;
    private int mFaceColor;
    private int mEmotion;
    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private float mEyeRadius;
    private float mEyeOffsetY;
    private float mEyeOffsetX;
    private RectF mArcBoundsFunny = new RectF();
    private RectF mArcBoundsSad = new RectF();
    private final static int FUNNY = 0,
                             SAD = 1;
    private final static String SUPER_STATE_TAG = "superState",
                                EMOTION_TAG = "emotion";
    private GestureDetectorCompat mGestureDetector;


    public SmileyView(Context context) {
        this(context, null);
    }

    public SmileyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmileyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        initPaints();
        initGestureDetector(context);
    }

    private void initGestureDetector(Context context){
        mGestureDetector = new GestureDetectorCompat(context, mGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        mGestureDetector.onTouchEvent(event);
        return true;
    }


    private GestureDetector.OnGestureListener mGestureListener =
            new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            changeEmotion();
            invalidate();
            requestLayout();
            return super.onSingleTapUp(e);
        }
    };

    private void changeEmotion(){
        switch (mEmotion){
            case FUNNY:
                mEmotion = SAD;
                break;
            case SAD:
                mEmotion = FUNNY;
                break;
        }
    }

    private void initAttrs(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SmileyView,
                0,0
        );

        try{
            mEyesColor = a.getColor(R.styleable.SmileyView_eyesColor,
                    ContextCompat.getColor(context, android.R.color.black));
            mMouthColor = a.getColor(R.styleable.SmileyView_mouthColor,
                    ContextCompat.getColor(context, android.R.color.black));
            mFaceColor = a.getColor(R.styleable.SmileyView_faceColor,
                    ContextCompat.getColor(context, android.R.color.white));
            mEmotion = a.getInt(R.styleable.SmileyView_emotion, 0);
        } finally {
            a.recycle();
        }
    }

    private void initPaints() {
        mFacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFacePaint.setStyle(Paint.Style.FILL);
        mFacePaint.setColor(mFaceColor);

        mEyesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyesPaint.setStyle(Paint.Style.STROKE);
        mEyesPaint.setStrokeWidth(getResources().getDisplayMetrics().density);
        mEyesPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyesPaint.setColor(mEyesColor);

        mMouthPaint = new Paint(mEyesPaint);
        mMouthPaint.setColor(mMouthColor);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSizes();
        // draw face
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mFacePaint);
        // draw eyes
        canvas.drawCircle(mCenterX - mEyeOffsetX, mCenterY - mEyeOffsetY, mEyeRadius, mEyesPaint);
        canvas.drawCircle(mCenterX + mEyeOffsetX, mCenterY - mEyeOffsetY, mEyeRadius, mEyesPaint);
        // draw mouth
        chooseEmotion(canvas);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE_TAG, super.onSaveInstanceState());
        bundle.putInt(EMOTION_TAG, mEmotion);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mEmotion = bundle.getInt(EMOTION_TAG);
            state = bundle.getParcelable(SUPER_STATE_TAG);
        }
        super.onRestoreInstanceState(state);
    }

    private void chooseEmotion(Canvas canvas){
        switch (mEmotion){
            case FUNNY:
                canvas.drawArc(mArcBoundsFunny, 45f, 90f, false, mMouthPaint);
                break;
            case SAD:
                canvas.drawArc(mArcBoundsSad, 225f, 90f, false, mMouthPaint);
                break;
        }
    }

    private void initSizes(){
        //eyes params
        mEyeRadius = mRadius / 5f;
        mEyeOffsetY = mRadius / 3f;
        mEyeOffsetX = mRadius / 3f;
        //mouth params
        float mMouthInset = mRadius / 3f;
        switch (mEmotion){
            case FUNNY:
                mArcBoundsFunny.set(mMouthInset, mMouthInset,
                        mRadius * 2 - mMouthInset, mRadius * 2 - mMouthInset);
                break;
            case SAD:
                mArcBoundsSad.set(mMouthInset, mRadius * 1.5f - mMouthInset,
                        mRadius * 2 - mMouthInset, mRadius * 3 - mMouthInset);
                break;
        }
    }
}
