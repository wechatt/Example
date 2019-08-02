package com.thundersoft.mi.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;

public class BasicViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view);
    }

    public void down(View view){
        switch (view.getId()){
            case R.id.listViewButton:
                startActivity(new Intent(this,MyListViewActivity.class));
                break;
            case R.id.multiListView :
                startActivity(new Intent(this,MultiListViewActivity.class));
                break;
            case R.id.menu:
                startActivity(new Intent(this, MenuActivity.class));
                break;
            case R.id.contextMenu:
                startActivity(new Intent(this, ContextMenuActivity.class));
                break;
            case R.id.subMenu:
                startActivity(new Intent(this, SubMenuActivity.class));
                break;
            case R.id.progressBar:
                startActivity(new Intent(this,ProgressBarActivity.class));
                break;
            case R.id.alertDialog:
                startActivity(new Intent(this,AlertDialogActivity.class));
                break;
            case R.id.progressDialog:
                startActivity(new Intent(this,ProgressDialogActivity.class));
                break;
            case R.id.notification:
                startActivity(new Intent(this,NotificationActivity.class));
                break;
            case R.id.srollTextView :
                startActivity(new Intent(this,ScrollTextViewActivity.class));
                break;
            case R.id.textViewScroll :
                startActivity(new Intent(this,TextViewScrollActivity.class));
                break;
            default :
                break;
        }
    }
}
