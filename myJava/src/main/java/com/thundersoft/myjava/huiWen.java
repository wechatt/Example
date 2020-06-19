package com.thundersoft.myjava;

public class huiWen {
    public static void main(String[] args){
        String s = "abaa";
        System.out.println("isHuiWen =" + isHuiWen(s));
    }

    public static boolean isHuiWen(String s){
        char[] chars = s.toCharArray();
        int length = s.length();
        for (int i=0;i<length-1;i++){
           if (chars[i] != chars[length-i-1]){
               return false;
           }
       }
        return true;
    }
}
