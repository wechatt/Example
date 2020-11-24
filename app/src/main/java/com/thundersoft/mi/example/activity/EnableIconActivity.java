package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.thundersoft.mi.example.R;

public class EnableIconActivity extends AppCompatActivity {
    private static final String TAG = "EnableIconActivity";
    private TextView isEnableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_icon);
        isEnableTextView = findViewById(R.id.isEnableTextView);
    }
    public void down(View v){
        switch (v.getId()){
            case R.id.enable:
                enableIcon();
                break;
        }
    }

    private void enableIcon() {
        final PackageManager packageManager = getPackageManager();
        String packageName = getPackageName();
        final ComponentName componentName = new ComponentName(this,packageName+ ".activity.MainActivity");
        int res = packageManager.getComponentEnabledSetting(componentName);
        Log.d(TAG, "enableIcon: res =" + res + "; packageName =" + packageName);
        if (res == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT
                || res == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            // 隐藏应用图标
            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP);
            isEnableTextView.setText("隐藏");
            /*isEnableTextView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                            PackageManager.DONT_KILL_APP);
                    isEnableTextView.setText("显示");
                }
            }, 4000);*/
        } else {
            // 显示应用图标
            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
                    PackageManager.DONT_KILL_APP);
            isEnableTextView.setText("显示");
        }
    }


}
