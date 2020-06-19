package com.thundersoft.myjava;

import java.util.Arrays;

public class fastSelect {
    public static void main(String[] args) {
        int[] arry = {33, 22, 43, 21, 34, 42, 56, 23};
        int[] ints = MergeSort(arry);
        sop(ints);
    }
    /**
     * 归并排序
     *
     * @param array
     * @return
     */
    public static int[] MergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        sop(left);
        System.out.println("left==============================");
        int[] right = Arrays.copyOfRange(array, mid, array.length);
        sop(right);
        System.out.println("right=============================");
        return merge(MergeSort(left), MergeSort(right));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        System.out.println("merge++++++++++++++");
        sop(result);
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length)
                result[index] = right[j++];
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        sop(result);
        System.out.println("merge================");
        return result;
    }

    public static void select(int[] arry, int left,int right, int[] temp){

    }

    private static void sop(int[] arry) {
        //遍历输出数组
        for (Integer i : arry) {
            System.out.print(i+",");
        }
    }

}
