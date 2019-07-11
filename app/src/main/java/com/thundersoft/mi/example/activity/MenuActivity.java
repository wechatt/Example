package com.thundersoft.mi.example.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
/**
 * @author TuYong
 * @create 19-6-26
 * @Describe
 * @Email tuyong0125@thundersoft.com
 * android一共有三种菜单
 * optionsMenu选项菜单
 * contextMenu上下文菜单
 * subMenu子菜单
 * 最常见的就是选项菜单optionsMenu
 * 疑问：
 * 1.menu的弹出方式，为啥有些menu从activity的底部弹出．有些在右上角的三个点中弹出
 */

    /**
     * AS中activity继承AppCompatActivity才会有ActionBar
     * showAsAction主要是针对这个菜单的显示起作用的，它有三个可选项
     * always：总是显示在界面上．如果所有的item都将showAsAction设置为always属性，则ActionBar上不会出现三个点的图标
     * never：不显示在界面上，只让出现在右边的三个点中;
     * ifRoom：如果有位置才显示，不然就出现在右边的三个点中
     * orderInCategory="100"设置优先级，值越大优先级越低
     *
     * 菜单的显示顺序受到showAsAction和orderInCategory的双重影响
     * 菜单的显示顺序优先看showAsAction的属性，showAsAction=always的显示级别高于其它的ifRoom等．
     * 当itemd都是showAsAction=always时，这时显示顺序受到orderInCategory的影响，orderInCategory越大，优先级越低
     */
    //首次进入方法执行顺序onCreate－onCreateOptionsMenu－onPrepareOptionsMenu
public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_menu);
    }

        /**
         *
         * @param menu
         * @return
         *            *
         * menu item同样支持click listener，但是这种方式在性能上没有onOptionItemSelected()节省，所以一般情况，
         * 我们仍会使用onOptionItemSelected()。但是click listener的优先级别比onOptionItemSelected()高，
         * 也就是菜单项点击后，先触发click listener，如果返回flase（未处理完，继续传递事件），才会进一步触发onOptionItemSelected()。
         */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /**
         * 此方法用于初始化菜单，其中menu参数就是即将要显示的Menu实例。 返回true则显示该menu,false 则不显示;
         * (只会在第一次初始化菜单时调用) Inflate the menu; this adds items to the action bar
         * if it is present.
         */
        Log.d(TAG,"onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.test_menu,menu);
        MenuItem item = menu.add(Menu.NONE, Menu.FIRST, 0, "设置click事件");
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {//先执行onMenuItemClick再执行onOptionsItemSelected
                Log.d(TAG,"onMenuItemClick");
                Toast.makeText(MenuActivity.this, "onMenuItemClick", Toast.LENGTH_SHORT).show();
                return true;
                //返回true则当点击"设置click事件"这个item时，不会执行onOptionsItemSelected方法，
                // 点击其它的item还是会执行onOptionsItemSelected，如果return false,则表明事件还要继续往下面传递
                //即要执行onOptionsItemSelected方法．
            }
        });

        /**
         * 将菜单项和intent关联，用户点击后，系统将自动执行startActivity(intent)。intent的优先级别低于onOptionItemSelected()。
         * 例在如下，当用户按菜单项1后，系统会打开browser，并打开指定的网页
         * 关联intent，该intent会在执行完onOptionsItemSelected()后执行，当然onOptionsItemSelected()要返回false。
         */
        MenuItem intentItem = menu.add(Menu.NONE, Menu.FIRST, 0, "启动intent");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://blog.csdn.net/flowingflying"));
        intentItem.setIntent(intent);
        return true;//返回true表示要显示menu,这个返回值在创建之初就是返回的true
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        /**
         * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
         * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
         * 首次进入会调用，当用户点击menu时，显示其它被隐藏的item,也会调用．
         * 此onPrepareOptionsMenu方法中的menu和onCreateOptionsMenu中的menu是同一个menu对象
         * 故再次inflate时，其实是在之前的menu中添加新的item，而之前menu中已经存在的item依然存在．
         */
        Log.d(TAG,"onPrepareOptionsMenu");
        //可以inflate将新的item加入到之前的menu中
        //getMenuInflater().inflate(R.menu.normal_mode_menu,menu);

        //也可以动态add添加新的item
        //可以在onCreateOptionsMenu或者 onPrepareOptionsMenu方法中来添加菜单
        /**
         * menu.add((int groupId, int itemId, int order, charsequence title) .setIcon(drawable ID)
         * 这样添加进来的item貌似只能显示在三个点中．
         * 1.组别，如果不分组的话就写Menu.NONE, 
         * 2.Id，这个很重要，Android根据这个Id来确定不同的菜单 
         * 3.顺序，哪个菜单项在前面由这个参数的大小决定 ,数字越大优先级越低
         * 4.文本，菜单项的显示文本.setIcon(R.drawable.cherry);
         * add()方法返回的是MenuItem对象，调用其setIcon()方法，为相应MenuItem设置Icon 
         */
        //menu.add(Menu.NONE, Menu.FIRST, 0, "新");
        //return true; //返回true表示要显示menu
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        /**
         * 每次菜单被关闭时调用. （菜单被关闭有三种情形，menu按钮被再次点击、back按钮被点击或者用户选择了某一个菜单项）
         * 实测发现这个方法并没有被调用过，不知道何时调用．
         */
        Log.d(TAG,"onOptionsMenuClosed");
        Toast.makeText(this,R.string.close_menu,Toast.LENGTH_SHORT).show();
        super.onOptionsMenuClosed(menu);
    }

        /**
         *
         * @param item
         * @return
         *   优先级别低于onMenuItemClick（)
         *   菜单项点击，不仅提供onOptionsItemSelected()的一种触发方式，我们将在后面试验其他的两种方法，
         *   如果我们希望将菜单项点击的事件传递下去，继续触发其他处理，则返回false，如果我们认为全部已经处理完，到此为止，
         *   不需要将事件传递下去，则返回true。如果采用return super.onOptionsItemSelected(item); 则返回值为flase，
         *   即系统缺省返回false。
         */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /**
         * 菜单项被点击时调用，也就是菜单项的监听方法。
         * 通过这几个方法，可以得知，对于Activity，同一时间只能显示和监听一个Menu 对象。
         *
         */
        Log.d(TAG,"onOptionsItemSelected");
        String title = "";
        switch (item.getItemId()){
            case R.id.test_menu:

                break;
            case R.id.test_menu2:
                break;
            case R.id.test_menu3:
                break;
            case R.id.test_menu4:
                break;
            case R.id.test_menu5:
                break;
            case R.id.test_menu6:
                break;
        }
        title = item.getTitle().toString();
        Toast.makeText(this,title,Toast.LENGTH_SHORT).show();
        return false;
        //return super.onOptionsItemSelected(item);
    }
}
