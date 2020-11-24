package com.example.myview.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import com.example.myview.R;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService extends Service {
    private static final String TAG = "TimerService";
    public TimerService() {
    }
private TimerTask tt = new TimerTask(){

    @Override
    public void run() {
        Log.d(TAG, "run: tuyong");
        Intent updateIntent =new Intent();
        updateIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        Bundle extras = new Bundle();
        extras.putIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{R.layout.new_app_widget});
        updateIntent.putExtras(extras);
        sendBroadcast(updateIntent);
    }
};
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: tuyong");
        Timer timer = new Timer();
        timer.schedule(tt,0,1000);
    }
}
