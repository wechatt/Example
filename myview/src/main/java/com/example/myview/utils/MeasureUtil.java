package com.example.myview.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class MeasureUtil {
    private static final String TAG = "MeasureUtil";
    //获取手机屏幕尺寸
    public static int[] getScreenSize(Context context){
        DisplayMetrics metric = new DisplayMetrics();
        if (context == null){
            Log.d(TAG, "getScreenSize: context == null");
            return new int[]{0,0};
        }
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return new int[]{width,height};
    }
}
