package com.example.myview.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myview.R;
import com.example.myview.utils.MeasureUtil;

/**
 * 准备画个圆环
 * drawCircle
 */
public class DrawCircleView extends View implements Runnable{
    private static final String TAG = "CustomView";
    private Paint mPaint;
    private Context mContext;
    private float radius;
    private int backgroundColor;
    private int measuredWidth;
    private int measuredHeight;


    //当直接在java中new出CustomView时,调用一个参数的构造方法
    public DrawCircleView(Context context) {
        this(context,null);
    }
    //当在xml中引用CustomView时,调用两个参数的构造方法
    public DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //为什么在构造方法里面初始化画笔，而不再onDraw中去初始化了？
        //因为onDraw会反复调用，会创建大量对象，所以不适合。
        Log.d(TAG, "CustomView: tuyong");
        mContext = context;
        initAttrs(attrs);
        initPaint();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.DrawCircleView);
        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.DrawCircleView_custom_view_color, Color.GRAY);
            radius = typedArray.getDimension(R.styleable.DrawCircleView_custom_view_radius, 0);
            typedArray.recycle();
        }
    }

    private void initPaint() {
/*        Paint paint = new Paint();
        paint.setAntiAlias(true);//为其设置了抗锯齿（一种让图像边缘显得更圆滑光泽动感的碉堡算法,耗性能)*/
        //实例化画笔并打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /*
         * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为红色
        mPaint.setColor(backgroundColor);

        /*
         * 设置描边的粗细，单位：像素px
         * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
         */
        mPaint.setStrokeWidth(30);

    }
/*  四个参数的构造方法很少用到
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw:");
        /**
         * 绘制圆环 这里用工具类获取手机的屏幕尺寸
         * 第一个参数表示圆心的横坐标,这里获取的是屏幕宽度的一半
         * 第二参数表示圆心的纵坐标，这里获取的是屏幕高度的一半
         * 第三个参数表示圆的半径
         */
        //canvas.drawCircle(MeasureUtil.getScreenSize((Activity)mContext)[0] / 2, MeasureUtil.getScreenSize((Activity)mContext)[1] / 2, radius, mPaint);
        /**
         * 这里的圆心数字取的是DrawCircleView横纵坐标的一半,这样无论DrawCircleView的坐标如何变化,
         * 圆心始终是DrawCircleView的中心
         */
        canvas.drawCircle(measuredWidth/2,measuredHeight/2,radius, mPaint);
    }

    @Override
    public void run() {
        /*
         * 确保线程不断执行不断刷新界面
         */
        while (true) {
            try {
                /*
                 * 如果半径小于200则自加否则大于200后重置半径值以实现往复
                 */
                Log.d(TAG, "run: radius ="+radius);
                if (radius <= 200) {
                    radius += 10;
                // 在主线程中刷新View
                // invalidate();
                    //在子线程中刷新view
                    postInvalidate();
                } else {
                    radius = 0;
                }
                // 每执行一次暂停40毫秒
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
