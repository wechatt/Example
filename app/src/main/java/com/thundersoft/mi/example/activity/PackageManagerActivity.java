package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class PackageManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_manager);
    }
    public void down(View v){
        switch (v.getId()){
            case R.id.enableIcon:
                startActivity(new Intent(this,EnableIconActivity.class));
                break;
        }
    }
}
