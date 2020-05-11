package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.os.Bundle;

import com.thundersoft.mi.example.R;

public class SatelliteSanModelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satellite_san_model);
        chooseScanModel();
        finish();
    }

    private void chooseScanModel() {
        boolean isChooseM7Country = isChooseM7Country();
        if(isChooseM7Country){


        }else{

        }
    }

    private boolean isChooseM7Country() {
        return true;
    }
}
