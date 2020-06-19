package com.thundersoft.lib1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class MyJava {
    public static void main(String[] args){
        int[] m = {2,3,-2,4};
        int[] n={5,7,4,0,-2,4,0,4,5,-5,-1};
        int[] c={3,-1,4};
        System.out.println(maxProduct(m));
    }

    public static int maxProduct(int[] nums) {
        if(nums.length<=0)
            return 0;
        int global_value = nums[0];
        int local_max = nums[0];
        int local_min = nums[0];
        int temp;
        int parseInt = Integer.parseInt("123");
        for(int i=1; i<nums.length; ++i){
            temp = local_max; // 暂时保存局部最大值
            System.out.println("temp =" + temp + ";global_value="+global_value+";local_ma="+local_max+";local_min="+local_min);
            local_max = max(max(nums[i], nums[i]*temp), nums[i]*local_min); // 更新局部最大值
            System.out.println("nums["+i+"]" + "= " + nums[i] + "; nums[i]*temp =" + nums[i]*temp+";nums[i]*local_min =" + nums[i]*local_min);
            local_min = min(min(nums[i], nums[i]*local_min), nums[i]*temp); // 更新局部最小值
            System.out.println("nums["+i+"]" + "= " + nums[i] + "; nums[i]*local_min ="+nums[i]*local_min+ ";nums[i]*temp="+nums[i]*temp);
            global_value = max(local_max, global_value);
        }
        return global_value;
    }

    public static int max(int a, int b){
        if (a>b)
            return a;
        else
            return b;
    }

    public static int min(int a, int b){
        if (a>b)
            return b;
        else
            return a;
    }
    //给定一个整数数组，要求找出该数组中乘积最大的子数组，并返回此子数组的乘积
    public static int getMaxProduct(int[] nums){
        int sum=1;
        int temp = 0;
        int start=0;
        int end =0;
        int currentMax = 0;
        for (int i=0;i<nums.length;i++){
            if (sum!=0 && i!=0){
                temp = sum;
            }
            System.out.println("temp =" + temp);
            sum = nums[i]*sum;
            System.out.println("sum =" + sum);
            if (sum>currentMax){
                end = i;
                currentMax = sum;
            }
            if(sum==0){
                if(i!=nums.length-1){
                    start = i;
                }
                if(temp>0){
                    currentMax = temp;
                }
                temp = 0;
                sum = 1;
            }
        }
        System.out.println("start="+start+ "; end ="+end);
       /* int[] tempArry = new int[end-start];
        StringBuilder sb = new StringBuilder();
        for (int i=0,j=start+1;i<end-start;i++,j++){
            tempArry[0]= nums[j];
            sb.append(nums[j]+",");
        }*/
        //System.out.println(sb.toString());
        return currentMax;
    }
}
