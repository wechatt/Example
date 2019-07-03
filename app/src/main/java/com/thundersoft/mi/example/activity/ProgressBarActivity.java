package com.thundersoft.mi.example.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.thundersoft.mi.example.R;

/**
 * @author tuyong
 * @create 2019/7/2
 * @Describe ProgressBar 用于在界面上显示一个进度条，表示我们的程序正在加载一些数据
 */
public class ProgressBarActivity extends AppCompatActivity {
    private Button progress_bt;
    private ProgressBar round_progress_bar;
    private ProgressBar horizontal_progress_bar;
    private Button load_progress_bar;
    private static final int UPDATE_PROGRESS = 1;
    private static final long TIME_INTERVAL = 1000;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
             switch (msg.what){
                 case UPDATE_PROGRESS:
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress + 10;
                     horizontal_progress_bar.setProgress(progress);
                     if (progress<100){
                         handler.sendEmptyMessageDelayed(UPDATE_PROGRESS,TIME_INTERVAL);
                     }else{
                         horizontal_progress_bar.setProgress(0);
                     }
                     break;
             }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        progress_bt = findViewById(R.id.progress_bt);
        round_progress_bar = findViewById(R.id.round_progress_bar);
        horizontal_progress_bar = findViewById(R.id.horizontal_progress_bar);
        progress_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   if (round_progress_bar.getVisibility()==View.INVISIBLE){
                       round_progress_bar.setVisibility(View.VISIBLE);
                   }else{
                       round_progress_bar.setVisibility(View.INVISIBLE);
                   }
            }
        });

        /**
         * 水平进度条
         * xml中通过style 属性配置为style="@style/Widget.AppCompat.ProgressBar.Horizontal"
         * xml中设置android:max="100"
         */
        load_progress_bar = findViewById(R.id.load_progress_bar);
        load_progress_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessageDelayed(UPDATE_PROGRESS,TIME_INTERVAL);
            }
        });
    }
}
