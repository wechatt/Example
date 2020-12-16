package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.utils.Utils;

public class SimulateEventActivity extends AppCompatActivity {
    private static final String TAG = "SimulateEventActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_event);
        Utils.printTaskId(this);
    }

    public void down(View v){
        switch (v.getId()){
            case R.id.anr :
                startActivity(new Intent(this,AnrTestActivity.class));
                break;
        }
    }
}
