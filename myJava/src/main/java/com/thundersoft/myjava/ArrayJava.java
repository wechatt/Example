package com.thundersoft.myjava;

import java.util.Arrays;

public class ArrayJava {
    public static void main(String[] args){
        String s = "-2147483647";
        parseInt(s);
        System.out.println("s="+s);
    }

    public static int parseInt2(String s){
        boolean isFuShu = false;
        int var = 10;
        int length = s.length();
        int min = -2147483647;
        int index =0;
        int var2=0;
        char firstNum = s.charAt(0);
        if (firstNum<'0'){
            if (firstNum == '-'){
                isFuShu = true;
                min = -2147483648;
            } else if (firstNum != '+'){
                throw new RuntimeException();
            }
            ++index;
        }
        int digit;
        for (int maxNum = min/var;index<length;var2-=digit){
             digit = Character.digit(s.charAt(index++), var);
             var2*=var;
        }
        return isFuShu?var2:-var2;
    }

    public static int parseInt(String var0, int var1) throws NumberFormatException {
        if (var0 == null) {
            throw new NumberFormatException("null");
        } else if (var1 < 2) {
            throw new NumberFormatException("radix " + var1 + " less than Character.MIN_RADIX");
        } else if (var1 > 36) {
            throw new NumberFormatException("radix " + var1 + " greater than Character.MAX_RADIX");
        } else {
            int var2 = 0;
            boolean var3 = false;
            int var4 = 0;
            int var5 = var0.length();
            int var6 = -2147483647;
            if (var5 > 0) {
                char var9 = var0.charAt(0);
                System.out.println("var9 =" + var9);
                if (var9 < '0') {
                    if (var9 == '-') {
                        var3 = true;
                        var6 = -2147483648;
                    } else if (var9 != '+') {
                        throw new RuntimeException();
                    }

                    if (var5 == 1) {
                        throw new RuntimeException();
                    }

                    ++var4;
                }

                int var8;
                System.out.println("var6="+var6 + ";var4="+var4+";var2="+var2);
                for(int var7 = var6 / var1; var4 < var5; var2 -= var8) {
                    var8 = Character.digit(var0.charAt(var4++), var1);
                    System.out.println("var7="+var7+";var4="+var4+"; var2="+var2+";var8="+var8);
                    if (var8 < 0) {
                        System.out.println("var8="+var8);
                        throw new RuntimeException();
                    }

                    if (var2 < var7) {
                        throw new RuntimeException();
                    }

                    var2 *= var1;
                    System.out.println("var2="+var2+";var6+var8="+(var6+var8));
                    if (var2 < var6 + var8) {
                        throw new RuntimeException();
                    }
                }

                return var3 ? var2 : -var2;
            } else {
                throw new RuntimeException();
            }
        }
    }

    public static int parseInt(String var0) throws NumberFormatException {
        return parseInt(var0, 10);
    }
}
