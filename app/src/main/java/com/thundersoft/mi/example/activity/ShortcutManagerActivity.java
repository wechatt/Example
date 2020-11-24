package com.thundersoft.mi.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.thundersoft.mi.example.R;
import com.thundersoft.mi.example.broadcast.ShortcutReceiver;

import java.util.Arrays;

public class ShortcutManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_manager);
    }
    public void down(View v){
        switch (v.getId()){
            case R.id.add_shortcut:
                ShortcutManager shortcutManager = (ShortcutManager)getSystemService(Context.SHORTCUT_SERVICE);
                if (shortcutManager.isRequestPinShortcutSupported()) {
                    Intent shortcutInfoIntent = new Intent(this, MainActivity.class);
                    shortcutInfoIntent.setAction(Intent.ACTION_VIEW);
                    //action必须设置，不然报错
                    ShortcutInfo info = new ShortcutInfo.Builder(this, "shortId")
                            .setIcon(Icon.createWithResource(this, R.drawable.apple))
                            .setShortLabel("Short Label")
                            .setIntent(shortcutInfoIntent)
                            .build();
                    //当添加快捷方式的确认弹框弹出来时，将回调ShortcutReceiver中的onReceive方法
                    PendingIntent shortcutCallbackIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, ShortcutReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
                    shortcutManager.requestPinShortcut(info, shortcutCallbackIntent.getIntentSender());
                }
                break;
                //点击后在桌面长按桌面图标可以看到3D　touch的效果。
            case R.id.add_shortcut2:
                ShortcutManager shortcutManager2 = getSystemService(ShortcutManager.class);
                ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1")
                        .setShortLabel("Web site")
                        .setLongLabel("Open the web site")
                        .setIcon(Icon.createWithResource(this, R.drawable.whitecat))
                        .setIntent(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://afra55.github.io/")))
                        .build();
                shortcutManager2.setDynamicShortcuts(Arrays.asList(shortcut));
                break;
        }
    }
}
