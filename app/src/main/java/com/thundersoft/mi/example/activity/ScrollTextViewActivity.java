package com.thundersoft.mi.example.activity;
import android.os.Bundle;

import android.util.Log;

import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.view.ScrollTextView;

import java.util.ArrayList;
import java.util.List;

public class ScrollTextViewActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ScrollTextView stvExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_text_view);

        stvExample = (ScrollTextView) findViewById(R.id.stv_example);

        List<String> textList = new ArrayList<>();
        List<ScrollTextView.OnScrollClickListener> clickListeners = new ArrayList<>();
        List<ScrollTextView.OnScrollListener> scrollListeners = new ArrayList<>();

        textList.add("The adolescent girl from Tennessee is standing on the stage of a drama " +
                "summer camp in upstate New York. It's a beautiful day. But the girl doesn't " +
                "feel beautiful. She's not the leggy, glamorous Hollywood type.");
        textList.add("一名少女由田纳西州来到纽约北部，她站在戏剧夏令营的舞台上，虽然天气是那么好，她的心情却一点也不好。");


        clickListeners.add(new ScrollTextView.OnScrollClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(ScrollTextViewActivity.this, "this is the text one", Toast.LENGTH_SHORT).show();
            }
        });

        clickListeners.add(new ScrollTextView.OnScrollClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(ScrollTextViewActivity.this, "this is the text two", Toast.LENGTH_SHORT).show();
            }
        });


        scrollListeners.add(new ScrollTextView.OnScrollListener() {
            @Override
            public void onScrollStart(List<ScrollTextView.TextInfo> passedTextInfos) {
                String text = "";
                for (ScrollTextView.TextInfo s : passedTextInfos) {
                    text = text + s.getText();
                }
                Log.d(TAG, "" + text);
            }

            @Override
            public void onScrollEnd(List<ScrollTextView.TextInfo> incommingTextInfos) {
                String text = "";
                for (ScrollTextView.TextInfo s : incommingTextInfos) {
                    text = text + s.getText();
                }
                Log.d(TAG, "" + text);
            }
        });

        stvExample.setTransferTime(1000);
        stvExample.setSpanTime(1200);
//        stvExample.setTextColor();
        stvExample.setTextSize(88);

        stvExample.setTextContent(textList, clickListeners, scrollListeners);
        stvExample.setTransferMode(ScrollTextView.TransferMode.TRANSFER_MODE_FADING);

        //Auto start,so you don't need this line,unless restart.
//        stvExample.startTextScroll();

        //you can also setTextContent in this way below.

//        stvExample.setTextContent(textList, clickListeners);
//        stvExample.setTextContent(textList);
//        stvExample.setTextContent(textList, null, scrollListeners);
        Log.e(TAG, "onCreate"+ "\n");

    }



}
