package com.thundersoft.mi.example.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.thundersoft.mi.example.R;

public class JavaActivity extends Activity {
    private static final String TAG = "JavaActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "handleMessage: tuyong",new ThreadDeath());

        }
    };

    public void javaDown(View v){
        switch (v.getId()){
            case R.id.collectionRepeatingElementNumber:
                handler.sendEmptyMessage(1);
                Log.d(TAG, "javaDown: tuyong");
                //showLogoutDialog();
                break;
            default :
                break;
        }
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        /*true 代表点击空白可消失   false代表点击空白哦不可消失 */
        builder.setCancelable(false);
        //View view = View.inflate(this, R.layout.dialog_global, null);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_global, null);
        TextView tvOk =   view.findViewById(R.id.id_logout_tv_ok);
        TextView tvCancel =   view.findViewById(R.id.id_logout_tv_cancel);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        //设置弹出全局对话框，但是这句话并不能解决在android的其他手机上能弹出来（例如用户华为p10 就无法弹框）
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        //只有这样才能弹框
        if (Build.VERSION.SDK_INT >= 26) {//8.0新特性 2038
            if (!Settings.canDrawOverlays(this)) {
                Log.d("tuyong","tuyong : 没有权限");
                //Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                //startActivity(intent);
                //return;
            } else {
                  //绘ui代码, 这里说明6.0系统已经有权限了
                Log.d("tuyong","tuyong : 已经有权限了");
            }
            //new ColorDrawable(0xE2343E83)
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0xE2343E83));
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);//2002
        //dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//2003
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
