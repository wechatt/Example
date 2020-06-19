package com.example.myview.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.lifecycle.ViewModelProvider;

import com.example.myview.MainActivity;
import com.example.myview.R;
import com.example.myview.service.TimerService;

import java.text.SimpleDateFormat;

import static android.content.ContentValues.TAG;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final int REQUEST_CODE = 123;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        ComponentName provider = new ComponentName(context, NewAppWidget.class);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String formatTime = sdf.format(time);
        views.setTextViewText(R.id.appwidget_text, formatTime);
        Log.d(TAG, "updateAppWidget: tuyong : setTextViewText2 =" + formatTime);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,REQUEST_CODE,intent,0);
        views.setOnClickPendingIntent(R.id.appwidget_text,pi);
        Log.d(TAG, "updateAppWidget: tuyong :appWidgetId="+appWidgetId+";R.layout.new_app_widget =" + R.layout.new_app_widget+";R.id.appwidget_text="+R.id.appwidget_text);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Log.d(TAG, "onUpdate: tuyong");
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        Log.d(TAG, "onEnabled: tuyong");
        Intent timerIntent = new Intent(context, TimerService.class);
        context.startService(timerIntent);
    }

    @Override
    public void onDisabled(Context context) {
        Log.d(TAG, "onDisabled: tuyong");
        // Enter relevant functionality for when the last widget is disabled
    }
}

