package com.thundersoft.mi.example.activity;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.thundersoft.mi.example.R;

public class ContentResolverPhoneActivity extends AppCompatActivity {

    private TextView mShowContact;
    private static final int TAG_PERMISSION = 12;
    private static final String TAG = "ContentPhoneActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver_phone);
        initView();
        initPermission();
    }

    private void initPermission() {
        Log.d(TAG,"checkSelfPermission="+ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS));
        //检查用户是否同意Manifest.permission.READ_CONTACTS权限，若同意checkSelfPermission返回0，反之返回-1.   PackageManager.PERMISSION_GRANTED的值是0
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"shouldShowRequestPermissionRationale = "+ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS));
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CONTACTS)){
                /**
                 * 用户已拒绝权限后，若调用此shouldShowRequestPermissionRationale，会返回true
                 * shouldShowRequestPermissionRationale，方法返回是一个boolean值，返回true表明用户之前已经选择过拒绝授权了；
                 * 若是返回false，则表明我们还有戏，用户还没有选择过是否授权~毕竟主动权是掌握在用户手中的，
                 * 此时我们就可以通过弹窗来询问用户时候同意授权了
                 */
                Toast.makeText(this,"deny for what???",Toast.LENGTH_SHORT).show();
            }else{
                Log.d(TAG,"show the request popupwindow");
                Toast.makeText(this, "show the request popupwindow", Toast.LENGTH_SHORT).show();
                /**
                 * 调用系统自带的弹窗询问用户是否授权
                 * 这时一个异步的方法，TAG_PERMISSION是我们自己定义的一个int类型常量（requestCode），
                 * 用于标记我们当前的方法。调用此方法，系统会弹出一个dialog，dialog的样式我们是无法修改的
                 */
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        TAG_PERMISSION);
            }
        }else{
            Log.d(TAG,"agreed");
            Toast.makeText(this,"agreed",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        mShowContact = findViewById(R.id.show_contact);
    }

    public void down(View v){
        switch (v.getId()){
            case R.id.content_resolver_phone_bt:
                getContact();
                break;
        }
    }

    private void getContact() {
        Cursor contactCursor = null;
        StringBuffer sb = new StringBuffer();
        try{
             //查询联系人数据
             contactCursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, null,
                    null, null);
             while(contactCursor.moveToNext()){
                 //获取联系人姓名
                 String displayName = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                 //获取联系人电话
                 String phoneNumber = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                 sb.append("displayName =" + displayName + "; phoneNumber =" + phoneNumber+"\n");
             }
            mShowContact.setText(sb);
        }catch (Exception e){
            Toast.makeText(this,"query fail",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }finally{
            if (contactCursor != null){
                contactCursor.close();
            }
        }
    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * 当用户点击权限框，无论是allow,或者deny都会回调此方法。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG,"onRequestPermissionsResult");
        Toast.makeText(this,"onRequestPermissionsResult",Toast.LENGTH_SHORT).show();
        switch (requestCode){
            case TAG_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult : allow");
                } else {
                    Log.d(TAG, "onRequestPermissionsResult : deny");
                }
            }
        }
    }
}
