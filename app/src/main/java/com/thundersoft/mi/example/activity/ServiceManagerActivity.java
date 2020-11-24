package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class ServiceManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_manager);
    }

    public void down(View v){
        switch (v.getId()){
            case R.id.shortcut_manager:
                startActivity(new Intent(this,ShortcutManagerActivity.class));
                break;
            case R.id.package_manager:
                startActivity(new Intent(this,PackageManagerActivity.class));
                break;
        }
    }
}
