package com.thundersoft.lib1;

public class fastSelect {
    public static void main(String[] args){
        int[] arry = {20,32,13,3,44,23,45,25,76};
        chaRu(arry);
        sot(arry);
    }

    //归并排序
    public static void sort(int[] arry,int left,int right,int[] temp){
        int mid = (left+right)/2;
           if (left < right) {
               sort(arry,left,mid,temp);//左边的排序
               sort(arry,mid+1,right,temp);//右边的排序
               merge(arry,left,right,temp);
           }
    }

    private static void merge(int[] arry, int left, int right, int[] temp) {
        int num=0;
        while(left<right){
            if(arry[left++]<arry[right++]){
                temp[num++]=arry[left];
            }
        }
    }

    //插入排序
    public static void chaRu(int[] arry){
        int temp;
        for(int i=0;i<arry.length;i++){
            temp = arry[i];
            int j=i;
            while( j>0 && temp<arry[j-1]){
                arry[j] = arry[j-1];
                j--;
            }
            arry[j] = temp;
        }
    }

    public static void fastSelcet(int[] arry,int low,int high){
        //由于low和high会参与后面的计算，值会发生变化，而我们后面递归的时候需要用到初始的low和high值，故这里先赋值记下来
        int left = low;
        int right = high;
        //由于后面会递归，我们一定要设置递归结束的条件，不然会一直调用下去
        if (low >high){
            return ;
        }
        //设置一个基础数，一般设置为数组第一位，原理就是找到这个base的在数组中的正确位置，让base左边的数全部小于base，base右边的数全部大于base
        int base = arry[low];
        //只要左右两边的角标没有遇到一起，就一直循环下去
        while(low<high){
            //从右往左找个比base小的数，然后结束循环；
            while(low<high && arry[high] >= base){
                high--;
            }
            //从左往右找到一个比base大的数
            while(low<high && arry[low] <= base){
                low++;
            }
            //交换两个数的位置
            int temp = arry[high];
            arry[high] = arry[low];
            arry[low] = temp;
        }
        //跳出while循环说明此时low等于high，两个角标相遇了，而由于是右边的角标先移动的，且右边角标停下来时说明找的数比base要小，所以交换base和数组第一个位置的数；
        arry[left] = arry[low];
        arry[low] = base;
        //此时base代表的数已经找到了正确的位置，我们只需要递归调用此函数，此时数组可以分为两部分即base左边数组，和base右边数组
        //base左边的数组，左角标依然是left,右角标是low-1;
        fastSelcet(arry,left,low-1);
        //base右边的数组，左角标为low+1,右角标为right;
        fastSelcet(arry,low+1,right);
    }
    private static void sot(int[] arry) {
        for (int i=0;i<arry.length;i++){
            System.out.print(arry[i]+";");
        }
    }
    private static void sop(int[] arry, int i, int j) {
        int temp=arry[j];
        arry[j]=arry[i];
        arry[i]=temp;
    }
}
