package com.thundersoft.myjava;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

public class ListJava {
/*    public static void main(String[] args){
        int[] arry = {33, 22, 43, 21, 34, 42, 56, 29};
        sort(arry);
        sop(arry);
    }
    public static void sort(int []arr){
        int []temp = new int[arr.length];
        sort(arr,0,arr.length-1,temp);
    }
    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);
            sort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }
    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }
        t = 0;

        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
    private static void sop(int[] arry) {

        for (Integer i : arry) {
            System.out.print(i+",");
        }
    }*/
}
