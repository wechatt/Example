package com.thundersoft.myjava;

import java.util.Scanner;

public class hanLuoTa {
    public static void main(String[] args){
        String sz=new String();
        Scanner scan=new Scanner(System.in);
        while(!sz.equals("-1"))
        {
            System.out.println("-------------------------------------\nplese input String");
            if(scan.hasNext())
            {
                sz=scan.next();
                if(sz.equals("-1"))
                    break;
            }
            if(isHuiwen(sz,0))
                System.out.println("is huiwen\n");
            else System.out.println("no is huiwen\n");
        }
        scan.close();
        System.out.println("over");
    }
    //abba, aba
    public static boolean isHuiwen(String s,int n)
    {
        int num=s.length();
        if(num/2!=n)
        {
            if(isHuiwen(s,n+1))
            {
                if(s.charAt(n)==s.charAt(num-n-1))
                    return true;
                else return false;
            }
        }
        else if(s.charAt(n)==s.charAt(num-n-1))
            return true;
        return false;
    }
}
