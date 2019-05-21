package com.thundersoft.mi.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void down(View view){
        switch (view.getId()){
            //四大组件
            case R.id.button_components:

                break;
            case R.id.basicView:
                startActivity(new Intent(this,BasicViewActivity.class));
                break;
            default:
                    break;
        }

    }
}
