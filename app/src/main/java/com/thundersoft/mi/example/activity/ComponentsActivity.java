package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class ComponentsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components);
    }

    public void click(View v){
        switch (v.getId()){
            //动态广播
            case R.id.dynamic_broadcast:
                startActivity(new Intent(this,DynamicBroadcastActivity.class));
                break;

        }

    }
}
