package com.example.myview.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myview.R;

public class MyTextView extends TextView {
    private static final String TAG = "tuyong";
    private int mIconSize = 200;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyTextView : dispatchTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.dispatchTouchEvent(event);
        Log.d(TAG, "MyTextView : dispatchTouchEvent: end : isConsum =" + isConsum);
        return isConsum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "MyTextView : onTouchEvent: start : event.getaction =" + event.getAction());
        boolean isConsum = super.onTouchEvent(event);
        Log.d(TAG, "MyTextView : onTouchEvent: end : isConsum =" + true);
        return true;
    }

    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //重要属性:
        setCompoundDrawablePadding(40);//设置图片和text之间的间距
        /**
         * setCompoundDrawables方法为TextView设置图片,需要传入一个Drawables;
         * Drawables是一个抽象类,BitmapDrawables是Drawables的子类
         */
        Drawable bitmapDrawables = getBitmapDrawables();
        setIcon(bitmapDrawables);
        //为textview设置文字
        setText("充气猫");
        //设置textview的内容为中心
        setGravity(Gravity.CENTER);
    }

    /**
     *给TextView设置图片
     * @param icon
     */
    private void setIcon(Drawable icon) {
       icon.setBounds(0,0,mIconSize,mIconSize);
       setCompoundDrawables(null,icon,null,null);
       //setCompoundDrawablesRelative(icon,null,null,null);
    }

    private Drawable getBitmapDrawables() {
        Resources res = getResources();
        //第一种获取drawable的方法
        //Drawable drawable = res.getDrawable(R.drawable.dani);
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.dani);
        BitmapDrawable bd= new BitmapDrawable(res, bmp);
        return bd;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 比如对于TextView，要通过TextView的getPaint()得到Paint，而不能new一个或者拿其他不相干的Paint
         * Paint.FontMetircs:这玩意儿是绘制文本内容时存储该文本内容位置信息的一个类。
         */
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        int cellHeightPx =  mIconSize + getCompoundDrawablePadding() + (int)Math.ceil(fm.bottom - fm.top);
        Log.d(TAG, "onMeasure: mIconSize =" +mIconSize+"; getCompoundDrawablePadding() =" +getCompoundDrawablePadding()+ "; fm.bottom="+fm.bottom+"; fm.top="+fm.top);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width =  MeasureSpec.getSize(widthMeasureSpec);
        //设置的是容器内部的padding值
        setPadding(getPaddingLeft(),200,getPaddingRight(),0);
        Log.d(TAG, "onMeasure: getPaddingLeft() =" + getPaddingLeft() + "; height =" + height + ";cellHeightPx ="+cellHeightPx+ "; width="+width+"; getPaddingRight() =" + getPaddingRight() + "; getPaddingBottom() =" + getPaddingBottom());
    }
}
