package com.example.myview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

public class MyRelativeLayout extends RelativeLayout {
    private static final String TAG = "tuyong";
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyLinearLayout : dispatchTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.dispatchTouchEvent(event);
        Log.d(TAG, "MyLinearLayout : dispatchTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyLinearLayout : onTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.onTouchEvent(event);
        Log.d(TAG, "MyLinearLayout : onTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}

        /*setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        button = findViewById(R.id.button);
        Log.d(TAG, "onCreate: imageView.isClickable =" + imageView.isClickable());
        Log.d(TAG, "onCreate: button.isClickable =" + button.isClickable());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: imageView --- onTouch ---action = " + event.getAction());
                return false;
            }
        });

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(TAG, "onTouch: button -- onTouch --- action ="+event.getAction());
                return false;
            }
        });
        Log.d(TAG, "onCreate: imageView.isClickable =" + imageView.isClickable());
        Log.d(TAG, "onCreate: button.isClickable =" + button.isClickable());*/