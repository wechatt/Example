package com.thundersoft.mi.example.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thundersoft.mi.example.R;

public class FeaturesActivity extends AppCompatActivity {

    private Button getPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        initView();
    }

    private void initView() {
        getPx = findViewById(R.id.getPx);
    }

    public void featureDown(View view){
        switch (view.getId()){
            case R.id.getPx:
                String px = getPx();
                getPx.setText(px);
                break;
        }
    }

    private String getPx() {
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        return "X :"+xdpi+" ; Y="+ydpi;

    }
}
