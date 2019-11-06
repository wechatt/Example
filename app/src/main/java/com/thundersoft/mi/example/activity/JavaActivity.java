package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
    }

    public void javaDown(View v){
        switch (v.getId()){
            case R.id.collectionRepeatingElementNumber:
                
        }
    }
}
