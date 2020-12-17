package com.thundersoft.mi.example.utils;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * 方法引用通过方法的名字来指向一个方法。 *
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。 *
 * 方法引用使用一对冒号 :: 。
 */

public class JavaNewFeature2 {
    private static final String TAG = "JavaNewFeature2";
    public interface Supplier<T> {
        T get();
    }

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static JavaNewFeature2 create(final Supplier<JavaNewFeature2> supplier) {
        return supplier.get();
    }

    public static void collide(final JavaNewFeature2 car) {
        Log.d(TAG, "collide: "+ car.toString());
    }

    public void follow(final JavaNewFeature2 another) {
        Log.d(TAG, "follow: "+ another.toString());
    }

    public void repair() {
        Log.d(TAG, "repair: "+ this.toString());
    }

    public static void main(String[] args) {
        //构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：
        JavaNewFeature2 car  = JavaNewFeature2.create(JavaNewFeature2::new);
        JavaNewFeature2 car1 = JavaNewFeature2.create(JavaNewFeature2::new);
        JavaNewFeature2 car2 = JavaNewFeature2.create(JavaNewFeature2::new);
        JavaNewFeature2 car3 = new JavaNewFeature2();
        List<JavaNewFeature2> cars = Arrays.asList(car,car1,car2,car3);
        Log.d(TAG, "===================构造器引用========================");
        //静态方法引用：它的语法是Class::static_method，实例如下：
        cars.forEach(JavaNewFeature2::collide);
        Log.d(TAG, "===================静态方法引用========================");
        //特定类的任意对象的方法引用：它的语法是Class::method实例如下：
        cars.forEach(JavaNewFeature2::repair);
        Log.d(TAG, "===================特定类的任意对象的方法引用========================");
        //特定对象的方法引用：它的语法是instance::method实例如下：
        final JavaNewFeature2 police = JavaNewFeature2.create(JavaNewFeature2::new);
        cars.forEach(police::follow);
        Log.d(TAG, "===================特定对象的方法引用========================");
    }
}
