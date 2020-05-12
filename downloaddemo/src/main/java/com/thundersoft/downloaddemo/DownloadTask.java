package com.thundersoft.downloaddemo;

import android.os.AsyncTask;
import android.os.Environment;

import com.thundersoft.downloaddemo.listener.DownloadListener;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author TuYong
 * @create 20-5-11
 * @Describe
 */
public class DownloadTask extends AsyncTask<String , Integer , Integer > {

    public static final int TYPE_SUCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;
    private DownloadListener listener;
    public boolean isPaused = false;
    public boolean isCanceled = false;
    private int lastProgress;

    public DownloadTask(DownloadListener listener){
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            long downloadLength = 0;
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory+fileName);
            if (file.exists()){
                downloadLength = file.length();
            }
            long contentLength = getContentLength(downloadUrl);
        }catch (Exception e){

        }
        return null;
    }

    private long getContentLength(String downloadUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(downloadUrl).build();
        //client.
    }
}
