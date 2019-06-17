package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thundersoft.mi.example.R;

public class DynamicBroadcastActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        DynamicBroadcastReceiver abr = new DynamicBroadcastReceiver();
        IntentFilter intf = new IntentFilter();
        intf.addAction("com.thundersoft.dynamic");
        registerReceiver(abr,intf);
        Button send_dynamic = findViewById(R.id.send_dynamic);
        send_dynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yong","sendBoadcast");
               sendBroadcast(new Intent("com.thundersoft.dynamic"));
            }
        });
    }
    //这里的修饰词public非常重要，若不写或换成其它修饰词则在AndroidManifest中注册会报错，接受不到广播　
    public class DynamicBroadcastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),"接受到动态广播",Toast.LENGTH_SHORT).show();
        }
    }
}
