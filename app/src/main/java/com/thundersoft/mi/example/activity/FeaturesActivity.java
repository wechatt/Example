package com.thundersoft.mi.example.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.utils.Utils;

public class FeaturesActivity extends AppCompatActivity {
    private static final String TAG = "FeaturesActivity";
    private Button getPx;
    private Button screen_size;
    private Button mkdir;
    private Button mkfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        initView();
    }

    private void initView() {
        getPx = findViewById(R.id.getPx);
        mkdir = findViewById(R.id.mkdir);
        mkfile = findViewById(R.id.mkfile);
        screen_size = findViewById(R.id.screen_size);
    }

    public void featureDown(View view){
        switch (view.getId()){
            case R.id.getPx:
                String px = getPx();
                getPx.setText(px);
                break;
            case R.id.screen_size:
                int[] size = getScreenSize();
                screen_size.setText("x ="+size[0] + "; y = " + size[1]);
                break;
            case R.id.news:
                startActivity(new Intent(this,NewsActivity.class));
                break;
            case R.id.mkdir:
                String dirPath = Utils.mkDirOrFile("MyDownload");
                mkdir.setText(dirPath);
                break;
            case R.id.mkfile :
                String filePath = Utils.mkDirOrFile("package.txt");
                ActivityManager am =(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                ConfigurationInfo info = am.getDeviceConfigurationInfo();
                //以16进制显示GLES版本
                String strResult = Integer.toString(info.reqGlEsVersion, 16);
                //假如是opengles 1.1 info.reqGlEsVersion= 0x00010001
                //假如是opengles 2.0 info.reqGlEsVersion=  0x00020000
                Log.d(TAG, "featureDown: info.reqGlEsVersion="+strResult);
                FeatureInfo[] systemAvailableFeatures = getPackageManager().getSystemAvailableFeatures();
                Log.d(TAG, "featureDown: systemAvailableFeatures.length="+systemAvailableFeatures.length);
                for (int i=0;i<systemAvailableFeatures.length;i++){
                    FeatureInfo feature = systemAvailableFeatures[i];
                    int flags = feature.flags;
                    String name = feature.name;
                    int reqGlEsVersion = feature.reqGlEsVersion;
                    int version = feature.version;
                    int describeContents = feature.describeContents();
                    String glEsVersion = feature.getGlEsVersion();
                    String s = feature.toString();
                    //Log.d(TAG, "featureDown: i="+i);
                    //Log.d(TAG, "featureDown: flags="+flags+"; name="+name+";reqGlEsVersion="+reqGlEsVersion+";version="+version+";describeContents="+describeContents+";glEsVersion="+glEsVersion);
                   // Log.d(TAG, "featureDown: toString ="+s);
                    //Utils.writeExternal("feature.txt",name);
                    Log.d(TAG, "featureDown: name="+name);
                    Log.d(TAG, "featureDown: =============================================");
                }
                mkfile.setText(filePath);
                break;
        }
    }

    /**
     * 获取屏幕尺寸
     * @return
     */
    private int[] getScreenSize() {
        //第一种方法
        /*DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        int heightPixels = outMetrics.heightPixels;
        Log.i(TAG, "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels);*/
        Display defaultDisplay = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int x = point.x;
        int y = point.y;
        return new int[]{x,y};
    }

    private String getPx() {
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        return "X :"+xdpi+" ; Y="+ydpi;

    }
}
