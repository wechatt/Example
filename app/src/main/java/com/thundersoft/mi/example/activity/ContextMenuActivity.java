package com.thundersoft.mi.example.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;

import java.util.ArrayList;

/**
 * @author TuYong
 * @create 19-6-27 *
 * @Email tuyong0125@thundersoft.com
 * @Describe 上下文菜单contextMenu
 * Android系统中的ContextMenu(上下文菜单)类似于PC中的右键弹出菜单，当一个视图注册到一个上下文菜单时，执行一个在该对象上的“长按”动作，
 * 将出现一个提供相关功能的浮动菜单。上下文菜单可以被注册到任何视图对象中，最常见的是用于列表视图ListView的item，在按中列表项时，
 * 会转换其背景色而提示将呈现上下文菜单。
 * 注意：上下文菜单不支持图标和快捷键。
 *
 *  为了创建一个上下文菜单，你必须重写这个活动的上下文菜单回调函数：onCreateContextMenu() 和 onContextItemSelected()。
 *  在回调函数onCreateContextMenu()里，你可以通过使用一个add()方法来添加菜单项，或者通过扩充一个定义在XML中的菜单资源。
 *  然后，通过registerForContextMenu()为这个视图注册一个上下文菜单ContextMenu. 
 */
public class ContextMenuActivity extends AppCompatActivity {

    private static final String TAG = "ContextMenuActivity";
    private ListView lv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);
        lv = findViewById(R.id.lv);
        tv = findViewById(R.id.contextMenuTv);

        ArrayList<String> data = new ArrayList<String>();
        for (int i=0;i<5;i++){
            data.add(""+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,data);
        lv.setAdapter(adapter);

        //注册视图对象，即为ListView控件注册上下文菜单
        registerForContextMenu(lv);
        registerForContextMenu(tv);
    }

    /**
     *
     * @param menu
     * @param v
     * @param menuInfo
     * 创建上下文菜单选项
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Log.d(TAG,"onCreateContextMenu");
        super.onCreateContextMenu(menu, v, menuInfo);
        //1.通过手动添加来配置上下文菜单选项
        //menu.add(0, 1, 0, "修改");
        //menu.add(0, 2, 0, "删除");

        //2.通过xml文件来配置上下文菜单选项
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.context_meun, menu);

    }

    /**
     *
     * @param item
     * @return
     * 当菜单的某个选项被点击时调用该方法
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d(TAG,"onContextItemSelected");
        switch (item.getItemId()){

            case R.id.add:
                tv.setVisibility(View.VISIBLE);
                break;
            case R.id.remove:
                tv.setVisibility(View.GONE);
                break;
            case R.id.update:
                break;

        }
        Toast.makeText(this,item.getTitle(),Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    /**
     * 当菜单关闭时调用
     * @param menu
     */
    @Override
    public void onContextMenuClosed(Menu menu) {
        Log.d(TAG,"onContextMenuClosed");
        super.onContextMenuClosed(menu);
    }
}
