package com.thundersoft.mi.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thundersoft.mi.example.R;
/**
 * @author TuYong
 * @create 19-6-26
 * @Describe
 * @Email tuyong0125@thundersoft.com
 */

    /**
     * AS中activity继承AppCompatActivity才会有ActionBar
     * showAsAction主要是针对这个菜单的显示起作用的，它有三个可选项
     * always：总是显示在界面上．如果所有的item都将showAsAction设置为always属性，则ActionBar上不会出现三个点的图标
     * never：不显示在界面上，只让出现在右边的三个点中;
     * ifRoom：如果有位置才显示，不然就出现在右边的三个点中
     * orderInCategory="100"设置优先级，值越大优先级越低
     */
public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
         * if it is present.
         */
        Log.d(TAG,"onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.test_menu,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /**
         * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
         * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
         *
         */
        Log.d(TAG,"onPrepareOptionsMenu");
        getMenuInflater().inflate(R.menu.normal_mode_menu,menu);
        return true;
        //return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        /**
         * 每次菜单被关闭时调用. （菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
         *
         */
        Log.d(TAG,"onOptionsMenuClosed");
        Toast.makeText(this,R.string.close_menu,Toast.LENGTH_SHORT).show();
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * 菜单项被点击时调用，也就是菜单项的监听方法。
         * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。
         *
         */
        Log.d(TAG,"onOptionsItemSelected");
        switch (item.getItemId()){
            case R.id.test_menu:
                break;
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }
}
