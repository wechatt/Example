package com.thundersoft.mi.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.thundersoft.mi.example.R;

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
        test = findViewById(R.id.test);
        String content = readTxtContent();
        test.setText(content);

    }

    public void down(View view){
        switch (view.getId()){
            //四大组件
            case R.id.button_components:
                startActivity(new Intent(this,ComponentsActivity.class));
                break;
            case R.id.basicView:
                startActivity(new Intent(this,BasicViewActivity.class));
                break;
            default:
                    break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_menu,menu);
        return true;
    }

    private String readTxtContent() {
        StringBuffer content = new StringBuffer();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.privacy);
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

