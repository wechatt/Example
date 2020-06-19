package com.thundersoft.myjava;

public class demo {
    public static void permutation(char[] s,int from,int to) {
        if(to <= 1){
            System.out.println("to return");
            return;
        }

        if(from == to) {
            System.out.println(s);
        } else {

            for(int i=from; i<=to; i++) {
                if(isNeedSwap(s,from,i)){
                    swap(s,i,from);
                    permutation(s, from+1, to);
                    swap(s,from,i);
                }
            }
        }
    }

    private static boolean isNeedSwap(char[] s, int from, int k) {
        boolean flag = true;
        for (int i = from; i < k; i ++) {
            if (s[i] == s[k]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void swap(char[] s,int i,int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }

    public static void main(String[] args) {
        char[] s = {'a','b','c'};
        permutation(s, 0, 2);
    }
}
