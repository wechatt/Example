package com.thundersoft.mi.example.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.view.TextViewScroll;

public class TextViewScrollActivity extends AppCompatActivity implements View.OnClickListener {

    private TextViewScroll tv_scroll;
    private Button from_right;
    private Button from_left;
    private Button from_top;
    private Button from_bottom;
    private Button start;
    private Button stop;
    private EditText et_speed;
    private Button btn_speed;
    private EditText et_content;
    private Button btn_settext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_scroll);
        initView();
        isAotuScroll();
    }

    private void isAotuScroll() {
        tv_scroll.post(new Runnable() {
            @Override
            public void run() {
                boolean isEllip = tv_scroll.isEllip();
                Log.d("yong","isEllip =" + isEllip);
                if(isEllip){
                    tv_scroll.setEllipsize(null);
                    tv_scroll.setEllipsize(TextUtils.TruncateAt.END);
                    //tv_scroll.setText(R.string.text_view_scroll_content);
                  /*  LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.FILL_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT*/
                    LinearLayout.LayoutParams p = (LinearLayout.LayoutParams)tv_scroll.getLayoutParams();
                    tv_scroll.setLayoutParams(p);
                   // tv_scroll.setEllipsize(TextUtils.TruncateAt.END);
                    tv_scroll.setText(et_content.getText());
                    Log.d("yong","setText ");
                    tv_scroll.setScrollType(TextViewScroll.FROM_TOP);
                }
            }
        });

    }

    private void initView() {
        TextView tx = findViewById(R.id.text);
        tv_scroll = (TextViewScroll) findViewById(R.id.tv_scroll);
        from_right = (Button) findViewById(R.id.from_right);
        from_right.setOnClickListener(this);
        from_left = (Button) findViewById(R.id.from_left);
        from_left.setOnClickListener(this);
        from_top = (Button) findViewById(R.id.from_top);
        from_top.setOnClickListener(this);
        from_bottom = (Button) findViewById(R.id.from_bottom);
        from_bottom.setOnClickListener(this);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(this);
        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);
        et_speed = (EditText) findViewById(R.id.et_speed);
        et_speed.setOnClickListener(this);
        btn_speed = (Button) findViewById(R.id.btn_speed);
        btn_speed.setOnClickListener(this);
        et_content = (EditText) findViewById(R.id.et_content);
        et_content.setOnClickListener(this);
        btn_settext = (Button) findViewById(R.id.btn_settext);
        btn_settext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.from_right:
                tv_scroll.setScrollType(TextViewScroll.FROM_RIGHT);
                break;
            case R.id.from_left:
                tv_scroll.setScrollType(TextViewScroll.FROM_LEFT);
                break;
            case R.id.from_top:
                tv_scroll.setScrollType(TextViewScroll.FROM_TOP);
                break;
            case R.id.from_bottom:
                tv_scroll.setScrollType(TextViewScroll.FROM_BOTTOM);
                break;
            case R.id.start:
                isAotuScroll();
                tv_scroll.setScrollStatus(true);
                break;
            case R.id.stop:
                tv_scroll.setScrollStatus(false);
                break;
            case R.id.btn_speed:
                tv_scroll.setSpeed(Integer.valueOf(et_speed.getText().toString()));
                break;
            case R.id.btn_settext:
                tv_scroll.setMaxLines(2);
                tv_scroll.setEllipsize(TextUtils.TruncateAt.END);
                tv_scroll.setText(et_content.getText());
                tv_scroll.setScrollStatus(false);
                break;
        }
    }

}
