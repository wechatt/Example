package com.thundersoft.mi.example.utils;

import android.util.Log;

public class JavaNewFeature {
    private static final String TAG = "JavaNewFeature";
    public static void start() {
        Log.d(TAG, "start: ");
        JavaNewFeature tester = new JavaNewFeature();
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;
        Log.d(TAG, "10 + 5 = " + tester.operate(10, 5, addition));
        Log.d(TAG, "10 - 5 = " + tester.operate(10, 5, subtraction));
        Log.d(TAG, "10 x 5 = " + tester.operate(10, 5, multiplication));
        Log.d(TAG, "10 / 5 = " + tester.operate(10, 5, division));
        // 不用括号 ,GreetingService的匿名子类,实现了sayMessage方法,这种表达式只有在GreetingService只有一个方法时才能使用
        GreetingService greetService1 = message ->
                Log.d(TAG, "start: "+message);
        //具体的调用
        greetService1.sayMessage("Runoob");
        //====================相当于下面==============================
        GreetingService g = new GreetingService() {
            @Override
            public void sayMessage(String message) {
                Log.d(TAG, "start: "+message);
            }
        };
        g.sayMessage("Runoob");
        //===========================================================
        // 用括号
        GreetingService greetService2 = (message) ->
                Log.d(TAG, "start: "+message);

        greetService2.sayMessage("Google");
        //System.out.println(String.valueOf(param + num));
        //============================================================
        /**
         * 在Lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。
         * lambda 表达式的局部变量可以不用声明为 final，但是必须不可被后面的代码修改（即隐性的具有final 的语义）
         * lambda 表达式只能引用标记了 final 的外层局部变量，这就是说不能在lambda 内部修改定义在域外的局部变量，否则会编译错误。
         */
        final int num = 1;
        Converter<Integer, String> s = (param) -> Log.d(TAG, String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3
    }
    interface MathOperation {
        int operation(int a, int b);
    }
    interface GreetingService {
        void sayMessage(String message);
    }
    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

}
