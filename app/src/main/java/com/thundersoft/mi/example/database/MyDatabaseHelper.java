package com.thundersoft.mi.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by tuyong
 * User: mi
 * Date: 2019/7/8
 * Time: 21:50
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context mContext;

    private static final String CREATE_BOOK = "create table book ("
                                             + "id integer primary key autoincrement,"
                                             + "name text,"
                                             + "author text,"
                                             + "pages integer,"
                                             + "price real,"
                                             + "category_id integer)";
    private static final String CREATE_CATEGORY = "create table category (id integer primary key autoincrement,category_name text,category_code integer)";
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"创建数据库了",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     //数据库升级的最佳写法
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
            case 2:
                db.execSQL("alter table book add column category_id integer");
            default :

        }
    }
}
