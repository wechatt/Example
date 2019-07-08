package com.thundersoft.mi.example.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.database.MyDatabaseHelper;

public class DatabaseActivity extends AppCompatActivity {

    private MyDatabaseHelper dabaseHelper;
    private SQLiteDatabase writableDatabase;
    private EditText mBookName;
    private EditText mBookAuthor;
    private EditText mBookPages;
    private EditText mBookPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        initView();
    }

    private void initView() {
        mBookName = findViewById(R.id.book_name);
        mBookAuthor = findViewById(R.id.book_author);
        mBookPages = findViewById(R.id.book_pages);
        mBookPrice = findViewById(R.id.book_price);
    }

    public void databaseDown(View v){
        switch (v.getId()){
            case R.id.create_database:
                createDatabase();
                break;
            case R.id.database_add:
                 add();
                 break;
        }
    }

    private void add() {
        ContentValues contentValues = new ContentValues();
        String name = mBookName.getText().toString();
        String author = mBookAuthor.getText().toString();
        String pages = mBookPages.getText().toString();
        String price = mBookPrice.getText().toString();
        int page = 0;
        double prices = 0;
        if (!TextUtils.isEmpty(pages)){
            page = Integer.parseInt(pages);
        }
        if (!TextUtils.isEmpty(price)){
            prices = Integer.parseInt(price);
        }
        contentValues.put("name",name);
        contentValues.put("author",author);
        contentValues.put("pages",page);
        contentValues.put("price",prices);
        writableDatabase.insert("book",null,contentValues);
        Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
    }

    private void createDatabase() {
        //new MyDatabaseHelper()并不会创建数据库，而是配置数据库的相关信息，如数据库的“名称”和“版本号”等
        dabaseHelper = new MyDatabaseHelper(this, "example", null, 1);
        //调用getWritableDatabase若数据库不存在则创建数据库，若数据库已经存在，则不执行任何操作。
        writableDatabase = dabaseHelper.getWritableDatabase();
    }
}
