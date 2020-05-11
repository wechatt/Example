package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thundersoft.mi.example.R;

public class DiseqcDialogActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseqc_dialog);
        Button bt = findViewById(R.id.button_components);
    }

    public void down(View view){

    }
}
