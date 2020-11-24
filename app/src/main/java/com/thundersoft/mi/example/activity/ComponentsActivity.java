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
            case R.id.fragment_life_cycle:
                startActivity(new Intent(this,FragmentActivity.class));
                break;
            case R.id.one_fragment_life_cycle:
                startActivity(new Intent(this,FragmentFullInActivity.class));
                break;
            case R.id.database_crud:
                startActivity(new Intent(this,DatabaseActivity.class));
                break;
            case R.id.content_resolver_phone:
                startActivity(new Intent(this,ContentResolverPhoneActivity.class));
                break;
            case R.id.multi_window:
                startActivity(new Intent(this,MultiWindowActivity.class));
                break;
        }

    }
}
