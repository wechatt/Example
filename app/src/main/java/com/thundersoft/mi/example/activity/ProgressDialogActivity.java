package com.thundersoft.mi.example.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thundersoft.mi.example.R;

/**
 * @author tuyong
 * @create 2019/7/2
 * @Describe ProgressDialog 和AlertDialog 有点类似，都可以在界面上弹出一个对话框，都能够屏蔽
 * 掉其他控件的交互能力。不同的是，ProgressDialog 会在对话框中显示一个进度条，一般是
 * 用于表示当前操作比较耗时，让用户耐心地等待。它的用法和AlertDialog 也比较相似，
 */
public class ProgressDialogActivity extends AppCompatActivity {
    private Button show_progress_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);
        show_progress_bt = findViewById(R.id.show_progress_bt);
        show_progress_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog dialog = new ProgressDialog(ProgressDialogActivity.this);
                dialog.setTitle("this is a title");
                dialog.setMessage("this is a message");
                dialog.setCancelable(true);
                dialog.show();
            }
        });
    }
}
