package com.example.myview.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.myview.view.MyFrameLayout;
import com.example.myview.view.MyRelativeLayout;
import com.example.myview.view.MyTextView;

public class DispacthTouchEventActivity extends AppCompatActivity {
    private static final String TAG = "tuyong";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getView();
        setContentView(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        String packageName = getPackageName();
        int taskId = getTaskId();
        Log.d(TAG,"packageName2 =" +packageName + "; taskId ="+taskId);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG, "MainActivity : dispatchTouchEvent: ev.getAction =" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MainActivity : onTouchEvent: event.getAction =" + event.getAction() );
        return super.onTouchEvent(event);
    }

    private View getView() {
        MyFrameLayout frameLayout = new MyFrameLayout(this);
        MyRelativeLayout relativeLayout = new MyRelativeLayout(this);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(800,800);
        fl.gravity = Gravity.CENTER;
        relativeLayout.setLayoutParams(fl);
        MyTextView textView = new MyTextView(this);
        RelativeLayout.LayoutParams ll = new RelativeLayout.LayoutParams(400,400);
        //设置gravity为父控件的center
        ll.addRule(RelativeLayout.CENTER_IN_PARENT);
        textView.setLayoutParams(ll);
        frameLayout.addView(relativeLayout);
        relativeLayout.addView(textView);
        frameLayout.setBackgroundColor(Color.RED);
        relativeLayout.setBackgroundColor(Color.BLUE);
        textView.setBackgroundColor(Color.GREEN);
        textView.setTextSize(10);
        return frameLayout;
    }
}
