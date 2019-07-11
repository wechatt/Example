package com.thundersoft.mi.example.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.fragment.NewsFragment;
import com.thundersoft.mi.example.fragment.dummy.DummyContent;

public class NewsActivity extends AppCompatActivity implements NewsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
