package com.thundersoft.downloaddemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    private Button flag;
    private Button clear;
    private Button set;
    private LinearLayout ll;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().getDecorView().getWindowToken();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_main_y);
        registerScreenActionReceiver();
        find();
        //initView();




    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("mmm", "onConfigurationChanged: ");
    }

    private void find() {
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
          set = findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
               // getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                set.setText("SYSTEM_UI_FLAG_LIGHT_STATUS_BAR");
                //getStatusBarColor();
                setStatusBarTextColor(getWindow(),true);*/
                imm.showSoftInput(edit, InputMethodManager.SHOW_FORCED);//SHOW_FORCED表示强制显示
set.setText("show");
            }
        });
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                setStatusBarTextColor(getWindow(),false);
                clear.setText("SYSTEM_UI_FLAG_VISIBLE");
               // getStatusBarColor();*/
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                clear.setText("hide");
            }
        });
        edit = findViewById(R.id.edit);
        flag = findViewById(R.id.flag);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                String text = edit.getText().toString();
                int num = Integer.parseInt(text);
                int old = num;
                getWindow().getDecorView().setSystemUiVisibility(num);
                flag.setText(getWindow().getDecorView().getSystemUiVisibility()+"");
                //old &= ~num;
                //clear.setText("&~ =" +old);
                getStatusBarColor();*/

            }
        });
        ll = findViewById(R.id.ll);
        ll.setBackgroundColor(Color.WHITE);
    }

    /**
     * 广播注册
     *
     */
    public void registerScreenActionReceiver() {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            //filter.addAction(Intent.ACTION_SCREEN_ON);
            Log.i("tuyong", "注册屏幕解锁、加锁广播接收者...");
            registerReceiver(new ScreenActionReceiver(), filter);
    }

    class ScreenActionReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            showOrHide(edit);
        }
    }

    //view为接受软键盘输入的视图，SHOW_FORCED表示强制显示
    public  void showOrHide( View view) {
        Log.d("tuyong", "showOrHide: ");


        //  imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);//SHOW_FORCED表示强制显示
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0); //强制隐藏键盘
    }

    public static void setStatusBarTextColor(Window window, boolean lightStatusBar) {
        // 设置状态栏字体颜色 白色与深色
        View decor = window.getDecorView();
        int ui = decor.getSystemUiVisibility();
        ui |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                ui |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                ui &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }
        decor.setSystemUiVisibility(ui);
    }
    public void getStatusBarColor(){
        /*int statusBarColor = getWindow().getStatusBarColor();
        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        Log.d("tuyong", "onCreate: statusBarColor = "+ statusBarColor+"; systemUiVisibility =" + systemUiVisibility);*/

    }
    private void initView() {
        //ll.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        set.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
        flag.setVisibility(View.GONE);
        ll.setBackgroundColor(Color.WHITE);
    }
}
