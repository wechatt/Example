package com.thundersoft.mi.example.activity;

import android.content.Intent;
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
