package com.example.myview;

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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tuyong";
    private ImageView imageView;
    private Button button;

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
isInMultiWindowMode();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
        MyLinearLayout linearLayout = new MyLinearLayout(this);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(800,800);
        fl.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(fl);
        MyTextView textView = new MyTextView(this);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(400,400);
        ll.gravity = Gravity.CENTER;
        textView.setLayoutParams(ll);
        frameLayout.addView(linearLayout);
        linearLayout.addView(textView);
        frameLayout.setBackgroundColor(Color.RED);
        linearLayout.setBackgroundColor(Color.BLUE);
        textView.setBackgroundColor(Color.GREEN);
        return frameLayout;
    }
}
