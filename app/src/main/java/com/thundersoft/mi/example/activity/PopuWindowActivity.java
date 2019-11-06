package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.thundersoft.mi.example.R;

public class PopuWindowActivity extends AppCompatActivity {

    private PopupWindow popupWindow;
    private Button btShowPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popu_window);
        btShowPopupWindow = findViewById(R.id.showPopupWindow);
        btShowPopupWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNoneEffect();
            }
        });
/*        有些場景下我們需要進入activity便顯示popupWindow,如果直接在onCreate中調用show出popupWindow是會報錯的
        如直接調用showNoneEffect();程序會閃退，這時我們可以發送一個延時消息如show();
        showNoneEffect();
        show( );*/
    }

    public void show( ){
        btShowPopupWindow.post( new Runnable( ) {
            @Override
            public void run() {
                showNoneEffect();
            }
        });
    }

    //无任何效果的弹窗
    private void showNoneEffect() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vPopupWindow = inflater.inflate(R.layout.popupwindow, null, false);//引入弹窗布局
        popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //popupWindow.setContentView(vPopupWindow);
        //设置背景透明
         addBackground();
        //设置进出动画
        popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
        //引入依附的布局
        View parentView = LayoutInflater.from(PopuWindowActivity.this).inflate(R.layout.activity_popu_window, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

}
