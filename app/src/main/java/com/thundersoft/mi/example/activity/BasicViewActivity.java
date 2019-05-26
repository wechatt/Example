package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class BasicViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view);
    }

    public void down(View view){
        switch (view.getId()){
            case R.id.listViewButton:
                startActivity(new Intent(this,MyListViewActivity.class));
                break;
            case R.id.multiListView :
                startActivity(new Intent(this,MultiListViewActivity.class));
                break;
            default :
                break;
        }
    }
}
