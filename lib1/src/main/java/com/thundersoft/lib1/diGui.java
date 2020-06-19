package com.thundersoft.lib1;

import javax.crypto.spec.PSource;

public class diGui {
    public static void main(String[] args){
        System.out.println(yang(5,5));
    }

   /* * 例如，下面给出了杨辉三角形的前4行：
            *      1
            *     1 1
            *    1 2 1
            *   1 3 3 1
            *  1 4 6 4 1
            * 1 5 10 10 5 1*/
    public static int yang(int row,int cum){
           //f(row,cum) = f(row-1,cum-1)+f(row-1,cum);
        if (cum >= 0 && cum <= row){
            if (row == cum || cum==0){
                return 1;
            }else {
                return yang(row-1,cum-1)+yang(row-1,cum);
            }
        }
        return -1;
    }

    //f(n) = f(n-1)+f(n-2);斐波拉契数列第n个数的值 1,1,2,3,5,8,13,21.34.55....
    public static int job(int n){
           if (n==1) return 1;
           if (n==2) return 1;
           System.out.println("n="+n + "; job("+(n-1)+") =" + job(n-1));
           System.out.println("job("+(n-2)+") =" + job(n-2));
           System.out.println("======================");
           return job(n-1)+job(n-2);
    }

    public static void method(int n){
        int sum=1;
         for (int i=1;i<=n;i++){
             sum *= i;
             System.out.println("QQ =" + sum);
         }
    }

     public static int jieChen(int n){
        if (n==1) return 1;
        int sum = n*jieChen(n-1);
         System.out.println("n="+n+"; sum =" +sum);
        return sum;
     }

    //计算N的阶层
    public static int getNum(int n,int sum){
        if (n==1){
            System.out.println("n==1; sum =" + sum);
            return sum;
        }
              sum = n*(n-1);

        System.out.println("n =" + n + "; sum ="+sum);
              return sum;
    }
}
