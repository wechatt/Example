package com.thundersoft.mi.example.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author TuYong
 * @create 19-7-11
 * @Describe
 *
 * Uri包含权限和路径组成
 * 权限一般都"包名/provider",用于区分不同应用的provider
 * 路径一般就是表名，用于区分同一apk下的表
 *
 * content://com.example.app.provider/table1
 * 这就表示调用方期望访问的是 com.example.app 这个应用的 table1 表中的数据
 *
 * content://com.example.app.provider/table1/1
 * 这就表示调用方期望访问的是 com.example.app 这个应用的 table1 表中 id 为 1 的数据。
 *
 * 内容 URI 的格式主要就只有以上两种，以路径结尾就表示期望访问该表中所有的数据， 以 id 结尾就表示期望访问该表中拥有相应 id 的数据。
 * 我们可以使用通配符的方式来分别匹配这两种格式的内容 URI，规则如下。
 * 1. *：表示匹配任意长度的任意字符
 * 2. #：表示匹配任意长度的数字
 *
 * 所以，一个能够匹配任意表的内容 URI 格式就可以写成：
 * content://com.example.app.provider/*
 *
 * 而一个能够匹配 table1 表中任意一行数据的内容 URI 格式就可以写成：
 * content://com.example.app.provider/table1/#
 */
public class ExampleProvider extends ContentProvider {


    private SQLiteDatabase writableDatabase;
    private static final int BOOK_DIR = 0;
    private static final int BOOK_ITEM = 1;
    private static final String AUTHORITY = "com.thundersoft.mi.example.provider";
    private static UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
    }

    /**
     * 初始化内容提供器的时候调用。通常会在这里完成对数据库的创建和升级等操作，
     * 返回 true 表示内容提供器初始化成功，返回 false 则表示失败。注意，只有当存在
     * ContentResolver 尝试访问我们程序中的数据时，内容提供器才会被初始化。
     * @return
     */
    @Override
    public boolean onCreate() {
        try{
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext(),"example",null,1);
            writableDatabase = myDatabaseHelper.getWritableDatabase();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param uri 确定查询哪张表,是一个Uri对象
     * @param projection 确定查询哪些列，是一个String[]数组，里面是需要查询的列名，一般传入null代表查询所有列，如传入new String[]{"name","age"},
     *                    则代表要查询"name"和"age"两列的数据
     * @param selection　约束查询哪些行,是个String数据，类似：　"name = ? and age = ?"
     * @param selectionArgs selection参数中"?"的值，是一个String[]数组，如: new String[]{"Tom","28"}
     * @param sortOrder 对结果进行排序
     * @return 返回一个cursor对象，里面包好了查询的结果
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor query = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                query = writableDatabase.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                /**
                 * 将内容 URI 权限之后的部分以“/”符号进行分割，并把分割后的结果放入到一个字符串列表中，
                 * 那这个列表的第 0 个位置存放的就是路径，第 1个位置存放的就是 id 了。
                 */
                String id = uri.getPathSegments().get(1);
                query = writableDatabase.query("book",projection,"id = ?" ,new String[]{id},null,null,sortOrder);
                break;
        }
        return query;
    }

    /**
     * 向内容提供器中添加一条数据。使用 uri 参数来确定要添加到的表，待添加的数据
     * 保存在 values 参数中。添加完成后，返回一个用于表示这条新记录的 URI。
     * @param uri 定要添加到的表
     * @param values 用户存储待添加的数据
     * @return　返回一条表示这条新记录的 URI
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = writableDatabase.insert("book", null, values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
        }

        return uriReturn;
    }

    /**
     * 从内容提供器中删除数据
     * @param uri 定删除哪一张表中的数据
     * @param selection 用于约束删除哪些行
     * @param selectionArgs
     * @return 被删除的行数将作为返回值返回
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int deleteBookId = -1;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                 deleteBookId = writableDatabase.delete("book", selection, selectionArgs);
                 break;
            case BOOK_ITEM:
                String deleteId = uri.getPathSegments().get(1);
                writableDatabase.delete("book","id = ?",new String[]{deleteId});
                break;
        }
        return deleteBookId;
    }

    /**
     * 更新内容提供器中已有的数据。
     * @param uri 确定更新哪一张表中的数据
     * @param values 新数据保存在values中
     * @param selection　用于约束更新哪些行
     * @param selectionArgs　
     * @return　受影响的行数将作为返回值返回
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int updateId = -1;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateId = writableDatabase.update("book", values, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String updateBookId = uri.getPathSegments().get(1);
                updateId = writableDatabase.update("book", values, "id = ?", new String[]{updateBookId});
                break;
        }
        return updateId;
    }

    /**
     * 用于获取 Uri 对象所对应的 MIME 类型。
     * @param uri
     * @return
     *所有的内容提供器都必须提供的一个方法胡用获取Uri的MIME类型.
     * 一个内容 URI 所对应的 MIME字符串主要由三部分组分，Android 对这三个部分做了如下格式规定。
     * 1. 必须以 vnd 开头。
     * 2. 如果内容 URI 以路径结尾，则后接 android.cursor.dir/，如果内容 URI 以 id 结尾，则后接 android.cursor.item/
     * 3. 最后接上 vnd.<authority>.<path>。
     * 所以，对于 content://com.example.app.provider/table1 这个内容 URI，它所对应的 MIME
     * 类型就可以写成：
     * vnd.android.cursor.dir/vnd.com.example.app.provider.table1
     *
     * 对于 content://com.example.app.provider/table1/1 这个内容 URI，它所对应的 MIME 类型
     * 就可以写成：
     * vnd.android.cursor.item/vnd.com.example.app.provider.table1
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.thundersoft.mi.example.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.thundersoft.mi.example.provider.book";
        }
        return null;
    }
}
