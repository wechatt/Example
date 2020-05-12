package com.thundersoft.downloaddemo.listener;

/**
 * @author TuYong
 * @create 20-5-11
 * @Describe
 */
public interface DownloadListener {
    //通知当前的下载进度 
    void onProgress(int progress);
    void onSuccesss();
    void onFailed();
    void onPaused();
    void onCanceled();
}
