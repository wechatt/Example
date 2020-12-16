package com.example.myview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myview.R;
import com.example.myview.view.DrawCircleView;

public class DrawCircleActivity extends AppCompatActivity {

    private DrawCircleView round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_circle);
        round = findViewById(R.id.custom_round);
        //DrawCircleView继承了Runnable
        new Thread(round).start();
    }
}
