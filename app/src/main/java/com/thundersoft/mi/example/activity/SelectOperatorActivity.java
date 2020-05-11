package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.adapter.oneTextViewAdapter;
import com.thundersoft.mi.example.modle.Operator;

import java.util.ArrayList;

public class SelectOperatorActivity extends Activity {

     TextView mTextviewOperatorDesc;
     ListView mListViewOperator;
     ArrayList<Operator> mOperatorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_select_operator);
        initView();
        initData();
        Settings.Global.putString(getContentResolver(), "digital_scan_Channel", "scan");
    }

    private void initData() {
        mOperatorData = new ArrayList<Operator>();
        mOperatorData.add(new Operator("Canal Digitaal",0,0));
        mOperatorData.add(new Operator("Tv Vlaanderen",1,1));
        mOperatorData.add(new Operator("Telesat",2,2));
        mOperatorData.add(new Operator("HD Austria",3,3));
        mOperatorData.add(new Operator("Skylink Cesko",4,4));
        mOperatorData.add(new Operator("Skylink Slovensko",5,5));
        mOperatorData.add(new Operator("Diveo",6,6));
        mOperatorData.add(new Operator("Others",-1,7));

        for (int i = 0; i < mOperatorData.size(); i++){

        }
        oneTextViewAdapter adapter = new oneTextViewAdapter(mOperatorData,this);
        mListViewOperator.setAdapter(adapter);
        mListViewOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTextviewOperatorDesc.setText(mOperatorData.get(position).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     }

    private void initView() {
        mTextviewOperatorDesc = findViewById(R.id.textview_operator＿desc);
        mListViewOperator = findViewById(R.id.listview_operator);
    }
}
