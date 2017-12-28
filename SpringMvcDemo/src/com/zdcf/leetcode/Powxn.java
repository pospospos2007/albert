package com.zdcf.leetcode;
//50. Pow(x, n)
//Implement pow(x, n).
//
//
//Example 1:
//
//Input: 2.00000, 10
//Output: 1024.00000
//Example 2:
//
//Input: 2.10000, 3
//Output: 9.26100
public class Powxn {
	public double myPow(double x, int n) {
        if(x==0){
            return 0;
        }
        if(n==0){
            return 1;
        }
        int half = n/2;
        if(n<0){
            x = 1/x;
            half = -half;
        }
        double  rs = myPow(x,half);
        if(n%2==0){
            return rs*rs;
        }else{
            return rs*rs*x;
        }
    }
}
