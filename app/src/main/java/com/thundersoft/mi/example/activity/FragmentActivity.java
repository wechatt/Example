package com.thundersoft.mi.example.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.LeftFragment;
import com.thundersoft.mi.example.fragment.ReplaceFragment;
import com.thundersoft.mi.example.fragment.RightItemFragment;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;

/**
 * @author TuYong
 * @create 19-7-4 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 *
 *　当fragment在布局文件中写死时，当其被替换replace时会有一些奇怪的现象，例如在activity_fragment.xml中
 * 　LeftFragmen和RightItemFragment都写死在布局中啦，
 * 当用ReplaceFragment来replac掉RightItemFragment时
 *
 * １：执行啦addToBackStack的情况下
 * RightItemFragment会依次执行:onPause-onStop-onDestroyView
 * 此时RigthItemFragment进栈，并没有被销毁
 * 当按返回键使ReplaceFragment出栈时，由于显示出来的RightItemFragment在布局中写死啦，所以它并不会执行任何
 * 生命周期方法．
 *
 * 还可以通过 onSaveInstanceState()方法来保存数据，因为进入停止状态的碎片有可能在系统内存不足的时候被回收。保存下来的数据在
 * onCreate()、onCreateView()和 onActivityCreated()这三个方法中你都可以重新得到，它们都含
 * 有一个 Bundle 类型的 savedInstanceState 参数。
 *
 */
public class FragmentActivity extends AppCompatActivity implements LeftFragment.OnFragmentInteractionListener , RightItemFragment.OnListFragmentInteractionListener , View.OnClickListener {

    private TextView right_bt;
    private boolean isRightFragment = true;
    private Fragment rightFragment;
    private static final String TAG = "FragmentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_fragment);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();//startForegroundService(Intent service)
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    private void initView() {
        right_bt = findViewById(R.id.right_bt);
        right_bt.setOnClickListener(this);
        /**
         * Activity中可以通过此方法获取布局文件中的Fragment实例
         */
        //rightFragment = getFragmentManager().findFragmentById(R.id.right_fragment);
    }

    /**
     *
     * @param v
     * app包下FragmentManager用
     * import android.app.FragmentManager;
     * Fragmentmanager  fragmentManager=getFragmentManager();
     * v-4包的FragmentManager用
     * import android.support.v4.app.FragmentManager;
     * FragmentManager   fragmentManager=getSupportFragmentManager()   获取
     * 但是getSupportFragmentManager()  有其运用范围，只能在部分activity中运用，
     * 当遇到getSupportFragmentManager()  没定义的问题时，修改下activity为FragmentActivity或者AppCompatActivity。
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_bt:
                Fragment replaceFragment ;
                if (isRightFragment){
                    isRightFragment = false;
                    replaceFragment = new ReplaceFragment();
                }else{
                    isRightFragment = true;
                    replaceFragment = new RightItemFragment();
                }
                /**
                 * addToBackStack(),
                 * 它可以接收一个名字用于描述返回栈的状态，一般传入 null 即可，可以模拟返回栈
                 * 若没有调用此方法，则按返回键会直接退出界面，若调用了此方法则被替换的Fragment
                 * 会被放到一个返回栈中，按返回键就会依次出栈，类似Activity,不会按一次返回键就退出界面了（即退出了该Fragment所依附的Activity）
                 *
                 */
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.right_layout,replaceFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
            default :
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

}
