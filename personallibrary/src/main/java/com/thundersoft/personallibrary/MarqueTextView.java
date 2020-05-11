package com.thundersoft.personallibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author TuYong
 * @create 20-1-10
 * @Describe
 */
public class MarqueTextView extends TextView {
    public MarqueTextView(Context context) {
        super(context);
    }

    public MarqueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //向这里看齐，可以尝试注释掉看效果。
    @Override
    public boolean isFocused() {
        return true;
    }
}
