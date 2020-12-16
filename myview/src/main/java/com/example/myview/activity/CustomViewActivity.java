package com.example.myview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myview.R;
import com.example.myview.view.DrawCircleView;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }

    public void down(View v){
        switch (v.getId()) {
            case R.id.draw_circle_view:
                startActivity(new Intent(this,DrawCircleActivity.class));
                break;
            case R.id.draw_arc_view:
                startActivity(new Intent(this,DrawArcActivity.class));
                break;
        }
    }
}
