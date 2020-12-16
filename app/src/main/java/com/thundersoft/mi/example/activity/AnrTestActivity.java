package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.utils.Utils;

public class AnrTestActivity extends AppCompatActivity {
    private static final String TAG = "AnrTestActivity";
    private static final int MSG_CODE = 0x001;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_CODE:
                    Toast.makeText(AnrTestActivity.this,"接收到消息", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr_test);
        Utils.printTaskId(this);
    }

    public void threadBlocking(View v){
        int i = 0;
        while (i<Integer.MAX_VALUE){
            android.util.Log.i(TAG,"无限循环->"+i);
        }
    }

    public void sendMessage(View v){
        Message message = handler.obtainMessage();
        message.what = MSG_CODE;
        handler.sendMessage(message);
    }
}
