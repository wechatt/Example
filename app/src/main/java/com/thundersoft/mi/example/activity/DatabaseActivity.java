package com.thundersoft.mi.example.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.database.MyDatabaseHelper;

/**
 * @author TuYong
 * @create 19-7-9 *
 * @Email tuyong0125@thundersoft.com
 * @Describe
 * SQL 的全称是 Structured Query Language
 */
public class DatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    private MyDatabaseHelper dabaseHelper;
    private SQLiteDatabase writableDatabase;
    private EditText mBookName;
    private EditText mBookAuthor;
    private EditText mBookPages;
    private EditText mBookPrice;
    private EditText mNeedUpdatePriceBookName;
    private EditText mNewBookPrice;
    private EditText mPagesEt;
    private TextView data;
    private Button mTransactionReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        createDatabase();
        initView();
    }

    private void initView() {
        Uri uri = Uri.parse("");
        Cursor query = getContentResolver().query(null, null, null, null, "");
        int columnCount = query.getColumnCount();
        //add
        mBookName = findViewById(R.id.book_name);
        mBookAuthor = findViewById(R.id.book_author);
        mBookPages = findViewById(R.id.book_pages);
        mBookPrice = findViewById(R.id.book_price);
        //update
        mNeedUpdatePriceBookName = findViewById(R.id.need_update_price_book_name);
        mNewBookPrice = findViewById(R.id.new_book_price);
        //delete
        mPagesEt = findViewById(R.id.need_to_delete_book_where_pages_is_not_requirements);
        //query
        data = findViewById(R.id.database_data);
        //使TextView具有滑动性，另外xml中还需要设置Android:scrollbars="vertical"
        data.setMovementMethod(ScrollingMovementMethod.getInstance());
        //事务
        mTransactionReplace = findViewById(R.id.transaction_replace);
        mTransactionReplace.setOnClickListener(this);
    }

    public void databaseDown(View v){
        switch (v.getId()){
            case R.id.create_database:
                createDatabase();
                break;
            case R.id.database_add:
                insert();
                 break;
            case R.id.database_update:
                update();
                break;
            case R.id.database_delete:
                delete();
                break;
            case R.id.database_delete_all:
                deleteAll();
                break;
        }
        query();
    }

    /**
     * SQLiteDatabase 中还提供了一个 query()方法用于对数据进行查询。
     * 这个方法的参数非常复杂，最短的一个方法重载也需要传入七个参数。那我们就先来看一下
     * 这七个参数各自的含义吧，第一个参数不用说，当然还是表名，表示我们希望从哪张表中查
     * 询数据。第二个参数用于指定去查询哪几列，如果不指定则默认查询所有列。第三、第四个
     * 参数用于去约束查询某一行或某几行的数据，不指定则默认是查询所有行的数据。第五个参
     * 数用于指定需要去 group by 的列，不指定则表示不对查询结果进行 group by 操作。第六个参
     * 数用于对 group by 之后的数据进行进一步的过滤，不指定则表示不进行过滤。第七个参数用
     * 于指定查询结果的排序方式，不指定则表示使用默认的排序方式。
     */
    private void query() {
        //查询book表中的所有数据
        Cursor cursor = writableDatabase.query("book", null, null, null, null, null, null);
        StringBuffer sb = new StringBuffer();
        if (cursor.moveToFirst()){
            do {
                //遍历cursor数据
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                sb.append("name =" + name + "; author =" + author + "; pages ="+ pages + "; price ="+ price +"\n");

            }while(cursor.moveToNext());

        }
        cursor.close();
        data.setText(sb);
        //SQL语句执行查询
        //writableDatabase.rawQuery("select * from book",null);
    }

    /**
     * 删除所有数据
     */
    private void deleteAll() {
        writableDatabase.delete("book",null,null);
    }

    /**
     * SQLiteDatabase 中提供了一个 delete()方法专门用于删除数据，这个方法接收三个参数，第一
     * 个参数仍然是表名，这个已经没什么好说的了，第二、第三个参数又是用于去约束删除某一
     * 行或某几行的数据，不指定的话默认就是删除所有行。
     */
    private void delete() {
        String pages = mPagesEt.getText().toString();
        if (TextUtils.isEmpty(pages)){
            Toast.makeText(this, "pages is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int result = writableDatabase.delete("book", "pages > ?", new String[]{pages});
        if (result==1){
            Toast.makeText(this, "delete book who＇s pages >"+pages+ " success", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "delete book who＇s pages >"+pages+ " fail", Toast.LENGTH_SHORT).show();
        }
        mPagesEt.setText("");
        //SQL语句删除数据库数据
        //writableDatabase.execSQL("delete from book where pages > ?",
        //                              new String[]{"200"});
    }

    /**
     * SQLiteDatabase 中也是提供了一个非常好用的 update()方法用于对数据进行更新，这个方法
     * 接收四个参数，第一个参数和 insert()方法一样，也是表名，在这里指定去更新哪张表里的数
     * 据。第二个参数是 ContentValues 对象，要把更新数据在这里组装进去。第三、第四个参数
     * 用于去约束更新某一行或某几行中的数据，不指定的话默认就是更新所有行。
     */
    private void update() {
        String bookName = mNeedUpdatePriceBookName.getText().toString();
        String newBookPrice = mNewBookPrice.getText().toString();
        if (TextUtils.isEmpty(bookName)|TextUtils.isEmpty(newBookPrice)){
            Toast.makeText(this,"数据不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        double bookPrice = Double.parseDouble(newBookPrice);
        ContentValues updateValues = new ContentValues();
        updateValues.put("price",bookPrice);
        int result = writableDatabase.update("book", updateValues, "name = ?", new String[]{bookName});
        if (result==1){
            Toast.makeText(this,"update price success",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"update price fail",Toast.LENGTH_SHORT).show();
        }
        mNeedUpdatePriceBookName.setText("");
        mNewBookPrice.setText("");
        //SQL语句进行数据库的更新
        //writableDatabase.execSQL("update book set price = ? where name = ?" ,
        //                             new String[] {"39.9","oneplus"});
    }

    /**
     * SQLiteDatabase 中提供了一个 insert()方法，这个方法就是专门用于添加数据的。它接收三个
     * 参数，第一个参数是表名，我们希望向哪张表里添加数据，这里就传入该表的名字。第二个
     * 参数用于在未指定添加数据的情况下给某些可为空的列自动赋值 NULL，一般我们用不到这
     * 个功能，直接传入 null 即可。第三个参数是一个 ContentValues 对象，它提供了一系列的 put()
     * 方法重载，用于向 ContentValues 中添加数据，只需要将表中的每个列名以及相应的待添加
     * 数据传入即可。
     */
    private void insert() {
        ContentValues addValues = new ContentValues();
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
            prices = Double.parseDouble(price);
        }

        addValues.put("price",prices);
        addValues.put("pages",page);
        addValues.put("name",name);
        addValues.put("author",author);

        writableDatabase.insert("book",null,addValues);
        //Android还提供了一套SQL来操作数据库的方法execSQL(String sql,Object[] bindArgs);
        //writableDatabase.execSQL("insert into book (name ,author ,pages ,price) values(?,?,?,?)",
        //                          new String[]{"金庸","天龙八部","234","45.9"});
        mBookName.setText("");
        mBookAuthor.setText("");
        mBookPages.setText("");
        mBookPrice.setText("");
        Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
    }

    private void createDatabase() {
        //new MyDatabaseHelper()并不会创建数据库，而是配置数据库的相关信息，如数据库的“名称”和“版本号”等
        dabaseHelper = new MyDatabaseHelper(this, "" +
                "", null, 1);
        //调用getWritableDatabase若数据库不存在则创建数据库，若数据库已经存在，则不执行任何操作。
        writableDatabase = dabaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.transaction_replace:
                transactionReplace();
        }
    }

    private void transactionReplace() {
        //开启事务
        writableDatabase.beginTransaction();
        try{
            writableDatabase.delete("book",null,null);
            /*if (true){
                //手动抛出异常
                throw new NullPointerException();
            }*/
            ContentValues transactionValues = new ContentValues();
            transactionValues.put("name","King of lion");
            transactionValues.put("author","Tom");
            transactionValues.put("pages",123);
            transactionValues.put("price",49.9);
            writableDatabase.insert("book",null,transactionValues);
            //事务成功
            writableDatabase.setTransactionSuccessful();
            Toast.makeText(this,"Transaction Successful",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
             e.printStackTrace();
            Toast.makeText(this,"Transaction Fail",Toast.LENGTH_SHORT).show();
        }finally{
            //结束事务
            writableDatabase.endTransaction();
        }



    }
}
