package com.thundersoft.mi.example.activity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.utils.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
       // ll.setForeground(getDrawable(R.drawable.apple));
        test = findViewById(R.id.test);
        String content = readTxtContent();
        test.setText(content);
        Button button = findViewById(R.id.button_components);
        Utils.printTaskId(this);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d(TAG, "onCreate: displayMetrics.densityDpi =" + displayMetrics.densityDpi);
    }

    public void down(View view){
        switch (view.getId()){
            //四大组件
            case R.id.button_components:
                startActivity(new Intent(this,ComponentsActivity.class));
                break;
                //基本控件
            case R.id.basicView:
                startActivity(new Intent(this,BasicViewActivity.class ));
                break;
                //需求
            case R.id.features_bt:
                startActivity(new Intent(this,FeaturesActivity.class));
                break;
                //java练习
            case R.id.java_bt:
                startActivity(new Intent(this,JavaActivity.class));
                break;
                //服务管理
            case R.id.manager:
                startActivity(new Intent(this,ServiceManagerActivity.class));
                break;
                //模拟事件
            case R.id.simulated_event:
                startActivity(new Intent(this,SimulateEventActivity.class));
                break;
            default:
                    break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: tuyong");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private String readTxtContent() {
        StringBuffer content = new StringBuffer();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.test);
            if (inputStream != null) {
                InputStreamReader inputReader = new InputStreamReader(inputStream);
                BufferedReader buffReader = new BufferedReader(inputReader);
                String line;
                while (( line = buffReader.readLine()) != null) {
                    content.append(line).append("\r\n");
                }
                inputStream.close();
            }
        }
        catch (java.io.FileNotFoundException e) {
            Log.d(TAG, "The File doesn't not exist.");
        }
        catch (IOException e) {
            Log.d(TAG, e.getMessage());
        }
        return content.toString();
    }
}

