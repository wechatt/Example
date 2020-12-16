package com.thundersoft.mi.example.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.LongDef;

import java.io.File;
import java.io.IOException;

public class Utils {
    private static final String TAG = "Utils";
    private Context mContext = MyApplication.getContext();
    public static void printTaskId(Activity activity){
        String packageName = activity.getPackageName();
        int taskId = activity.getTaskId();
        Log.d(TAG,"packageName = "+ packageName + "; taskId2 =" + taskId);
    }

    public static void sys(String tag,String s){
        Log.d(tag,"s ="+ s);
    }


    /**
     * 获取到sd卡的根目录，并以String形式返回
     *
     * @return
     */
    public static String getSDCardPath() {
        String path = Environment.getExternalStorageDirectory() + "/";
        Log.d(TAG, "getSDCardPath: path ="+path);
        return path;
    }
    /**
     * 创建文件或文件夹
     *
     * @param fileName
     *            文件名或问文件夹名
     */
    public static String mkDirOrFile(String fileName) {
        File file = new File(getSDCardPath() + fileName);
        if (fileName.indexOf(".") != -1) {
            // 说明包含，即创建文件, 返回值为-1就说明不包含.,即是文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("创建了文件");
            Log.d(TAG, "mkDirOrFile: 创建了文件"+fileName + "; file.getAbsolutePath="+file.getAbsolutePath()+"; file.getPath ="+file.getPath());
        } else {
            // 创建文件夹
            file.mkdir();
            System.out.println("创建了文件夹");
            Log.d(TAG, "mkDirOrFile: 创建了文件夹"+fileName+";file.getAbsolutePath="+file.getAbsolutePath());
        }

        return file.getAbsolutePath();
    }

}
