package com.thundersoft.mi.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ShortcutReceiver extends BroadcastReceiver {
    private static final String TAG = "ShortcutReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "onReceive: tuyong : intent.getaction="+intent.getAction());
    }
}
