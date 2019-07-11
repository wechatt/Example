package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class NotificationActivity extends AppCompatActivity {

    private static final String CHANNELLD = "id";
    private static final int REQUESTCODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void down(View v){
        switch (v.getId()){
            case R.id.send_notification:
                sendNotification();
                break;
        }
    }

    private void sendNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //在API15即Android4.0.3时该方法已被弃用
        //Notification notification = new Notification(R.drawable.apple, "this is a notification", 1000);
        Intent intent = new Intent(this,AlertDialogActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this,REQUESTCODE,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder.setContentTitle("this is a title")
                .setContentText("content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.apple)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setChannelId("com.thundersoft.mi.example")
                .build();
        manager.notify(1,notification);
    }
}
