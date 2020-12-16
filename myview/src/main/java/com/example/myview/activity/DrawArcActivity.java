package com.example.myview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.myview.R;
import com.example.myview.view.DrawArcView;

public class DrawArcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_arc);
        DrawArcView drawArcView = findViewById(R.id.draw_arc);
        new Thread(drawArcView).start();
    }
}
