package com.thundersoft.mi.example.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.thundersoft.mi.example.R;

/**
 * @author tuyong
 * @create 2019/7/2
 * @Describe AlertDialog 可以在当前的界面弹出一个对话框，这个对话框是置顶于所有界面元素之上
 * 的，能够屏蔽掉其他控件的交互能力，因此一般AlertDialog 都是用于提示一些非常重要的
 * 内容或者警告信息。比如为了防止用户误删重要内容，在删除前弹出一个确认对话框。
 */
public class AlertDialogActivity extends AppCompatActivity {
    private Button dialog_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        dialog_bt = findViewById(R.id.dialog_bt);
        dialog_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("this is a title");
        dialog.setMessage("this is a message");
        /**
         * setCancelable(true)设置为true时，当点击返回键或者dialog区域外时也可以让dialog消失
         * 设置为false ,这只有点击dialog上的"ok"或者"cancel"时才能取消dialog
         */
        dialog.setCancelable(false);
        /**
         * 当setCancelable(false)时，点击返回键不能让dialog消失，这时我们可以
         * 给dialog添加KeyListener来对返回键予以处理就可以解决这个问题了
         */
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK){
                    Toast.makeText(AlertDialogActivity.this, "click back", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                return false;
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
