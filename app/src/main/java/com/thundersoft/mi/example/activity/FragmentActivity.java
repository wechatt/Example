package com.thundersoft.mi.example.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.LeftFragment;
import com.thundersoft.mi.example.fragment.RightItemFragment;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;

public class FragmentActivity extends AppCompatActivity implements LeftFragment.OnFragmentInteractionListener , RightItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
