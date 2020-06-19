package com.example.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class MyFrameLayout extends FrameLayout {
    private static final String TAG = "tuyong";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MyFrameLayout : dispatchTouchEvent: start : ev.getaction =" + ev.getAction());
        boolean isConsum = super.dispatchTouchEvent(ev);
        Log.d(TAG, "MyFrameLayout : dispatchTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyFrameLayout : onTouchEvent: start : event.getAction =" + event.getAction());
        boolean isConsum = super.onTouchEvent(event);
        Log.d(TAG, "MyFrameLayout : onTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
