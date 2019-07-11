package com.thundersoft.mi.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;

/**
 * @author TuYong
 * @create 19-6-27 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 * 每个Activity都关联一个且是唯一一个menu对象，当对该menu进行创建，将触发onCreateOptionsMenu()。
 */
public class SubMenuActivity extends AppCompatActivity {

    private static final String TAG = "SubMenuActivity";
    final int FONT_10 = 0X111;
    final int FONT_11 = 0X112;
    final int FONT_14 = 0x113;
    final int FONT_16 = 0x114;
    final int FONT_18 = 0x115;
    //定义“普通菜单项” 的标识
    final int PLAIN_ITEM = 0x11b;
    //定义“字体颜色”的菜单项的标识
    final int FONT_RED = 0x116;
    final int FONT_BLUE = 0x117;
    final int FONT_GREEN = 0x118;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);
        editText = findViewById(R.id.subMenuEt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG,"onCreateOptionsMenu");
        //添加字体大小的子菜单
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        //为fontMenu设置菜单图标
        fontMenu.setIcon(R.drawable.apple);
        //为fontMenu设置菜单头的图标
        fontMenu.setHeaderIcon(R.drawable.cherry);
        //设置菜单头的标题
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0,FONT_10,0,"10号字体");
        fontMenu.add(0,FONT_11,0,"11号字体");
        fontMenu.add(0,FONT_14,0,"14号字体");
        fontMenu.add(0,FONT_16,0,"16号字体");
        fontMenu.add(0,FONT_18,0,"18号字体");
        //向menu中添加普通菜单
        menu.add(0,PLAIN_ITEM,0,"普通子菜单");
        //添加颜色的子菜单
        SubMenu colorMenu = menu.addSubMenu("选择颜色");
        colorMenu.setHeaderIcon(R.drawable.ic_launcher_background);
        colorMenu.setHeaderTitle("字体颜色");
        colorMenu.add(0,FONT_BLUE,0,"蓝色");
        colorMenu.add(0,FONT_GREEN,0,"绿色");
        colorMenu.add(0,FONT_RED,0,"红色");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected");
        switch (item.getItemId()){
            case FONT_10:
                break;
        }
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.d(TAG,"onOptionsMenuClosed");
        super.onOptionsMenuClosed(menu);
    }
}
