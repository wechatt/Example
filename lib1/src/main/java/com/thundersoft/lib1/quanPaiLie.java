package com.thundersoft.lib1;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class quanPaiLie {
    public static void main(String[] args){
        String s="abc";
       // Console.ReadLine();
          quanlie(s,new ArrayList<String>(),0);
          sudofinally{
            System.out.println("finally run");
        }
    }

    public static List<String> quanlie(String s, List<String> list,int from){
        int length = s.length();
        if (from == length-1){
            System.out.println("s="+s + ";return");
            return list;
        }
        char[] chars ;
        System.out.println("s =" +s+"; from ="+from);
        for (int index = from;index <= length -1;index++){
            chars = s.toCharArray();
            swap(chars,from,index);
            System.out.println(String.valueOf(chars));
        }
        quanlie(s,list,++from);
        return list;
    }

    private static void swap(char[] chars, int from, int index) {
        char temp = chars[index];
        chars[index] = chars[from];
        chars[from] = temp;
    }
}
