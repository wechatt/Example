package com.thundersoft.mi.example.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.CenterFragment;
import com.thundersoft.mi.example.fragment.LeftFragment;
import com.thundersoft.mi.example.fragment.RightItemFragment;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;
/**
 * @author TuYong
 * @create 19-7-5 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 * 当fragment首次展示时，会依次执行：onAttach－onCreate－onCreateView－onActivityCreated－onStart－onResume
 *　07-05 10:46:43.342 12632 12632 D RightItemFragment: onAttach
 * 07-05 10:46:43.342 12632 12632 D RightItemFragment: onCreate
 * 07-05 10:46:43.342 12632 12632 D RightItemFragment: onCreateView
 * 07-05 10:46:43.345 12632 12632 D RightItemFragment: onActivityCreated
 * 07-05 10:46:43.345 12632 12632 D RightItemFragment: onStart
 * 07-05 10:46:43.345 12632 12632 D RightItemFragment: onResume
 *
 * 当fragment被替换replace时，
 *
 * 1:若执行啦addToBackStack则fragment会执行：onPause-onStop-onDestroyView
 *　07-05 10:54:00.119 12632 12632 D LeftFragment: onPause
 * 07-05 10:54:00.119 12632 12632 D LeftFragment: onStop
 * 07-05 10:54:00.119 12632 12632 D LeftFragment: onDestroyView
 *
 * 此时若按返回键则显示出来的fragment会依次执行：onCreateView-onActivityCreated-onStart-onResume
 * 也就是说当fragment从栈中显示出来时生命周期会先从onCreateView开始直到onResume
 * 有一种情况例外，就是fragment不是动态添加的，而是直接在xml中静态写死啦，这个时候第一场进入就会加载该
 * fragment，若该fragment被替换replace了，按返回键不停的出栈直至该fragment重新显示出来，这时
 * 该fragment不会执行任何生命周期方法，且其它动态添加的fragment被replace时，其界面都会都会消失，但是
 * 布局xml中写死的fragment即便被替换啦，它的界面还在，也就是说如果替换它的界面时透明的，那么用户还是可以看到
 * 该fragment的界面．所以一般不建议把fragment写死在xml中．
 *　07-05 10:59:17.021 12632 12632 D LeftFragment: onCreateView
 * 07-05 10:59:17.024 12632 12632 D LeftFragment: onActivityCreated
 * 07-05 10:59:17.024 12632 12632 D LeftFragment: onStart
 * 07-05 10:59:17.025 12632 12632 D LeftFragment: onResume
 *
 * 而出栈的fragment则会依次执行：onPause-onStop-onDestroyView-onDestroy-onDetach
 *
 *　07-05 10:59:17.025 12632 12632 D CenterFragment: onPause
 * 07-05 10:59:17.025 12632 12632 D CenterFragment: onStop
 * 07-05 10:59:17.025 12632 12632 D CenterFragment: onDestroyView
 * 07-05 10:59:17.026 12632 12632 D CenterFragment: onDestroy
 * 07-05 10:59:17.026 12632 12632 D CenterFragment: onDetach
 *
 * 2:若没有执行addToBackStack,则按返回键则会退出当前Activity,fragment会依次执行：onPause-onStop-onDestroyView-onDestroy-onDetach
 * 07-05 10:57:36.520 12632 12632 D CenterFragment: onPause
 * 07-05 10:57:36.520 12632 12632 D CenterFragment: onStop
 * 07-05 10:57:36.520 12632 12632 D CenterFragment: onDestroyView
 * 07-05 10:57:36.520 12632 12632 D CenterFragment: onDestroy
 * 07-05 10:57:36.520 12632 12632 D CenterFragment: onDetach
 *
 */
public class FragmentFullInActivity extends AppCompatActivity implements CenterFragment.OnFragmentInteractionListener , RightItemFragment.OnListFragmentInteractionListener, LeftFragment.OnFragmentInteractionListener {

    private static final String TAG = "FragmentFullInActivity";
    private Button center_bt;
    private Button left_bt;
    private Button right_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_fragment_full_in);
        center_bt = findViewById(R.id.center_bt);
        center_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CenterFragment centerFragment = new CenterFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_full_in_layout,centerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        left_bt = findViewById(R.id.left_bt);
        left_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LeftFragment leftFragment = new LeftFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_full_in_layout,leftFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        right_bt = findViewById(R.id.right_bt);
        right_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RightItemFragment rightItemFragment = new RightItemFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_full_in_layout,rightItemFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
