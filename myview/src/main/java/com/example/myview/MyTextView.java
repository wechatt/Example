package com.example.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyTextView extends TextView {
    private static final String TAG = "tuyong";

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyTextView : dispatchTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.dispatchTouchEvent(event);
        Log.d(TAG, "MyTextView : dispatchTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyTextView : onTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.onTouchEvent(event);
        Log.d(TAG, "MyTextView : onTouchEvent: end : isConsum =" + true);
        return true;
    }

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
