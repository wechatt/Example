package com.example.myview.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.myview.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void down(View v){
        switch (v.getId()){
            case R.id.diapatch:
                startActivity(new Intent(this,DispacthTouchEventActivity.class));
                break;
            case R.id.custom_view:
                startActivity(new Intent(this, CustomViewActivity.class));
                break;
        }
    }
}
