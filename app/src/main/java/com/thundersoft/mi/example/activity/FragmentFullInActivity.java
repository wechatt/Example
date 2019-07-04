package com.thundersoft.mi.example.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.CenterFragment;
import com.thundersoft.mi.example.fragment.LeftFragment;
import com.thundersoft.mi.example.fragment.RightItemFragment;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;

public class FragmentFullInActivity extends AppCompatActivity implements CenterFragment.OnFragmentInteractionListener , RightItemFragment.OnListFragmentInteractionListener, LeftFragment.OnFragmentInteractionListener {

    private static final String TAG = "FragmentFullInActivity";
    private Button center_bt;
    private Fragment fragment_in_activity;
    private Button left_bt;
    private Button right_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_fragment_full_in);
        fragment_in_activity = getFragmentManager().findFragmentById(R.id.fragment_in_activity);
        center_bt = findViewById(R.id.center_bt);
        center_bt.setOnClickListener(new View.OnClickListener() {
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

        left_bt = findViewById(R.id.left_bt);
        left_bt.setOnClickListener(new View.OnClickListener(){
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

        right_bt = findViewById(R.id.right_bt);
        right_bt.setOnClickListener(new View.OnClickListener() {
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
