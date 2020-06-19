package com.thundersoft.lib1;
import java.lang.NumberFormatException;
public class parseInt {
    public static void main(String[] args){
             String s = "1231212";
             //int num = parseInt(s);
        int num = Integer.parseInt(s);
             Integer.toString(123);
        int digit = Character.digit('h', 15);
        char m = '&';
        int and = 8&0;//0111
        Integer integer = 4;
        int bigcount = Integer.bitCount(6);
        System.out.println("num="+num + "; digit="+digit+ "; m ="+(int)m + ";8>>2="+(8>>1));
        System.out.println(digit('z',36));
    }
    public static int digit(char a,int b){
        return digit((int)a,b);
    }

    public static int digit(int codePoint, int radix) {
               if (radix < 2 || radix > 36) {
                      return -1;
                  }
            if (codePoint < 128) {
                    // Optimized for ASCII
                 int result = -1;
                     if ('0' <= codePoint && codePoint <= '9') {
                           result = codePoint - '0';
                          } else if ('a' <= codePoint && codePoint <= 'z') {
                             result = 10 + (codePoint - 'a');
                         System.out.println("z = result =" +result);
                           } else if ('A' <= codePoint && codePoint <= 'Z') {
                             result = 10 + (codePoint - 'A');
                           }
            return result < radix ? result : -1;
       }
       return  0;//digitImpl(codePoint, radix);
   }

    /*public static int digit(int ch, int radix) {
        int value = -1;
        if (radix >= Character.MIN_RADIX && radix <= Character.MAX_RADIX) {
            int val = getProperties(ch);
            int kind = val & 0x1F;
            if (kind == Character.DECIMAL_DIGIT_NUMBER) {
                value = ch + ((val & 0x3E0) >> 5) & 0x1F;
            }
            else if ((val & 0xC00) == 0x00000C00) {
                // Java supradecimal digit
                value = (ch + ((val & 0x3E0) >> 5) & 0x1F) + 10;
            }
        }
        return (value < radix) ? value : -1;
    }*/

    public static void myParseInt(String var0){
        int stringLength = var0.length();
        boolean isFuShu=false;
        if (stringLength > 0) {
            char firstNum = var0.charAt(0);
            if (firstNum =='-'){
                isFuShu = true;

            }
        }
    }



    public static int parseInt(String var0)throws NumberFormatException{
        return parseInt(var0, 10);
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
            System.out.println("var5 : length ="+var5);
            if (var5 > 0) {
                char var9 = var0.charAt(0);
                System.out.println("var9="+var9);
                System.out.println("0 =" + '0' + "; - =" + '-');
                if (var9 < '0') {
                    if (var9 == '-') {
                        var3 = true;//说明是负数
                        var6 = -2147483648;// -2^31,我们知道这是64位系统中int型能表示的最小值
                    } else if (var9 != '+') {
                        throw new RuntimeException();
                    }

                    if (var5 == 1) {
                        throw new RuntimeException();
                    }

                    ++var4;//角标数
                }

                int var8;
                System.out.println("var6="+var6+";var1="+var1 + "; var4 =" + var4);
                for(int var7 = var6 / var1; var4 < var5; var2 -= var8) {
                    var8 = Character.digit(var0.charAt(var4++), var1);
                    System.out.println("var7 =" + var7 + "; var8 =" + var8 + "; var2 =" + var2);
                    if (var8 < 0) {
                         throw new RuntimeException();
                    }

                    if (var2 < var7) {
                        throw new RuntimeException();
                    }


                    System.out.println("(var2*=var1) =" + (var2 *= var1) + "; var6 =" + var6+"; var8="+var8);
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
}

