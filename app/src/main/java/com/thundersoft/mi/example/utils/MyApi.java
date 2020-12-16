package com.thundersoft.mi.example.utils;

import android.util.Log;

public class MyApi {
    private static final String TAG = "MyApi";
    public static int myParseInt(String s) {
        return myParseInt(s, 10);
    }

    public static int myParseInt(String s, int radix) {
        if (s == null) {
            throw new NumberFormatException("s==null");
        }
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + "less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + "greater than Character.MIN_RADIX");
        }
        int result = 0;
        int i = 0, len = s.length();
        boolean negative = false;
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;
        if (len > 0) {
            char firstChar = s.charAt(0);
            //firstChar<'0',说明很有可能是加减号.
            if (firstChar < '0') {
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException("");

                if (len == 1)// Cannot have lone "+" or "-"
                    throw new NumberFormatException("");
                i++;//执行到这里说明第一位要么是'-'要么是'+'
            }
            //计算机允许的最大二进制数为Integer.MAX_VALUE,这里除以radix是因为下面result会乘以radix.
            multmin = limit/radix;
            Log.d(TAG, "myParseInt: multmin ="+ multmin);
            while (i<len){
                digit = Character.digit(s.charAt(i++),radix);
                Log.d(TAG, "myParseInt: digit ="+ digit);
                if (digit < 0){
                    throw new NumberFormatException("");
                }
                //因为下面result会乘以radix,所以这里必须先小于multmin,不然可能会超过计算机允许的最大数字。
                if (result < multmin){
                    throw new NumberFormatException("");
                }
                result *= radix;
                Log.d(TAG, "myParseInt: result*=radix =" + result);
                if (result < limit + digit) {
                    throw new NumberFormatException(s);
                }
                result -= digit;
                Log.d(TAG, "myParseInt: result -= digit =" + result);
            }
        }else {
            throw new NumberFormatException(s);
        }
        return negative ? result : -result;
    }

    public static int digit(int codePoint, int radix){
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX) {
            throw new NumberFormatException("");
        }
        if (codePoint < 128){
            //说明是ASCII
            int result = -1;
            if ('0' <= codePoint && '9' >= codePoint){
                result = codePoint - '0';
            }
            //因为a代表的是10,所以如果是这里加了10
            if ('a' <= codePoint && 'z' >= codePoint){
                result = 10 + codePoint - 'a';
            }
            if ('A' <= codePoint && 'Z' >= codePoint){
                result = 10 + codePoint - 'A';
            }
            //radix代表是进制。只有在进制内的result才能正确返回。
            return result < radix ? result : -1;
        }
        return -1;
    }
}
