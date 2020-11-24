package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.thundersoft.mi.example.R;

public class MultiWindowActivity extends AppCompatActivity {
    private static final String TAG = "MultiWindowActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean inMultiWindowMode = isInMultiWindowMode();
        Log.d(TAG, "onCreate: tuyong : inMultiWindowMode = "+inMultiWindowMode);
        if (inMultiWindowMode){
            setContentView(R.layout.activity_multi_window);
        } else {
            setContentView(R.layout.activity_normal_window);
        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: tuyong");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: tuyong");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: tuyong");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: tuyong");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: tuyong");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: tuyong");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        Log.d(TAG, "onMultiWindowModeChanged: tuyong");
    }
}
