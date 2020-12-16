package com.example.myview.view;

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

public class DrawArcView extends View implements Runnable{
    private static final String TAG = "DrawArcView";
    private Paint mPaint;
    private int backgroundColor = Color.GRAY;
    private int progressColor = Color.BLUE;
    private float radius;
    private int progress;
    //圆心横坐标
    private float centerX = 0;
    //圆心纵坐标
    private float centerY = 0;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    private int gravity = CENTER;
    private RectF rectF;  //用于定义的圆弧的形状和大小的界限
    private float startAngle;

    public DrawArcView(Context context) {
        this(context,null);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initParams(context, attrs);

    }
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        rectF = new RectF();
    }

    private void initParams(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DrawArcView);
        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.DrawArcView_percent_background_color, Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.DrawArcView_percent_progress_color, Color.BLUE);
            radius = typedArray.getDimension(R.styleable.DrawArcView_percent_circle_radius, 0);
            progress = typedArray.getInt(R.styleable.DrawArcView_percent_circle_progress, 0);
            gravity = typedArray.getInt(R.styleable.DrawArcView_percent_circle_gravity, CENTER);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        Log.e(TAG, "onMeasure--widthMode-->" + widthMode);
        Log.e(TAG, "onMeasure--widthSize-->" + widthSize + ";widthMeasureSpec ="+widthMeasureSpec);
        Log.e(TAG, "onMeasure--heightMode-->" + heightMode);
        Log.e(TAG, "onMeasure--heightSize-->" + heightSize+ ";heightMeasureSpec ="+heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY://
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        //onMeasure中需要用getMeasuredWidth()才能获取到值,如果用getWidth()获取到的值为0;
        int with = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.e(TAG, "onDraw---->" + with + "*" + height + "; gravity =" +gravity);
        switch (gravity) {
            case LEFT:
                //圆形在左边.圆心的横坐标就是半径radius的距离
                centerX = radius;
                //圆形在左边.圆心的纵坐标就是view的高度加上getPaddingTop()的一半
                centerY = (height+getPaddingTop())/2;
                break;
            case TOP:
                //圆形在顶部.圆心的横坐标就是view宽度的一半的距离
                centerX = with/2;
                //圆形在顶部.圆心的纵坐标就是半径radius的距离
                centerY = radius;
                break;
            case CENTER:
                //圆形在中间.圆心的横坐标就是view宽度的一半的距离
                centerX = with / 2;
                //圆形在顶部.圆心的纵坐标就是view高度的一半的距离
                centerY = height / 2;
                break;
            case RIGHT:
                //圆形在右边.圆心的横坐标就是view宽度的减去半径的距离
                centerX = with - radius;
                //圆形在右边.圆心的纵坐标就是view高度的一半的距离
                centerY = height / 2;
                break;
            case BOTTOM:
                //圆形在底部.圆心的横坐标就是view宽度的一半
                centerX = with/2;
                //圆形在底部.圆心的纵坐标就是view高度的减去半径的距离
                centerY = height - radius;
                break;
        }
        Log.e(TAG, "onMeasure--with-->" + with);
        Log.e(TAG, "onMeasure--height-->" + height);
        Log.e(TAG, "onMeasure--centerX-->" + centerX + "; centerY =" + centerY + "; radius ="+radius);
        Log.e(TAG, "onMeasure--getPaddingLeft()-->" + getPaddingLeft() + "; getPaddingTop() =" + getPaddingTop());
        Log.e(TAG, "onMeasure--getPaddingRight()-->" + getPaddingRight() + "; getPaddingBottom() =" + getPaddingBottom());

        /**
         * rectF是画圆的区域
         * left是矩形的左边距
         * top是矩形的上边距
         * right是矩形的右边距
         * botton是矩形的下边距
         *            left                  right
         * **************************************************>X轴
         * *           *                      *
         * *           *                      *
         * top****************************************************
         * *           *                      *
         * *           *   画图区域            *
         * *           *                      *
         * bottom****************************************************
         * *           *                      *
         * *           *                      *
         * *           *                      *
         * *           *                      *
         * *
         * Y轴
         */
        float left = centerX - radius;
        float top = centerY - radius;
        float right = centerX + radius;
        float bottom = centerY + radius;
        //圆弧区域的矩形，圆弧的中心点为矩形的中心点
        rectF.set(left, top, right, bottom);
        Log.e(TAG, "onMeasure--left-->" + left + "; top =" + top + "; right ="+right + "; bottom ="+bottom);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //onLayout中getWidth()和getHeight()才能获取到值。
        int widthOnLayout = getWidth();
        int heightOnLayout = getHeight();
        Log.e(TAG, "onLayout" + "; widthOnLayout =" + widthOnLayout + "; heightOnLayout =" + heightOnLayout);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        /**
         * 画圆,背景圆
         * centerX为圆心的横坐标
         * centerY为圆心的纵坐标
         * radius为圆的半径
         */
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        mPaint.setColor(progressColor);
        double percent = progress * 1.0 / 100;
        int angle = (int) (percent * 360);
        /**
         * 第一个参数：oval为确定圆弧区域的矩形，圆弧的中心点为矩形的中心点
         * 第二个参数：startAngle为圆弧的开始角度（时钟3点的方向为0度，顺时钟方向为正）
         * 第三个参数：sweepAngle为圆弧的扫过角度（正数为顺时钟方向，负数为逆时钟方向）
         * 第四个参数：useCenter表示绘制的圆弧是否与中心点连接成闭合区域
         * 第五个参数：paint为绘制圆弧的画笔
         */
        canvas.drawArc(rectF, startAngle, angle, true, mPaint);  //根据进度画圆弧
    }

    @Override
    public void run() {
       while(true){
           try {
               Log.d(TAG, "run: startAngle =" + startAngle);
               if (startAngle  < 360){
                   startAngle += 1;
                   postInvalidate();
               }else{
                   startAngle = 0;
               }
               Thread.sleep(10);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

    }
}
