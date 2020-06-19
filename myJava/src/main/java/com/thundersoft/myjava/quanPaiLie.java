package com.thundersoft.myjava;

public class quanPaiLie {
    public static void main(String[] args){
           char[] s = {'a','b','c'};
            paiLie(s,0,2);
    }
    public static void paiLie(char[] chars,int from,int to){
      // System.out.println( "; from =" +from);
        int start = from;
        if (from == to) {
            System.out.println(String.valueOf(chars));
            return;
        }
        for (int index= from;index<=to;index++){
            swap(chars,index,from);
            paiLie(chars,from+1,to);
            swap(chars,from,index);
        }

    }

    private static void swap(char[] chars, int index, int from) {
        char temp = chars[from];
        chars[from] = chars[index];
        chars[index] = temp;
    }
}
