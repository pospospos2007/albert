package com.zdcf.leetcode;
//70. Climbing Stairs
//You are climbing a stair case. It takes n steps to reach to the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//Note: Given n will be a positive integer.
//
//
//Example 1:
//
//Input: 2
//Output:  2
//Explanation:  There are two ways to climb to the top.
//
//1. 1 step + 1 step
//2. 2 steps
//Example 2:
//
//Input: 3
//Output:  3
//Explanation:  There are three ways to climb to the top.
//
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
public class ClimbingStairs {
	public int climbStairs(int n) {
        if(n<1){
            return 0;
        }
        if(n<=2){
            return n;
        }
        int[] a = new int[n];
        a[0]= 1;
        a[1]=2;
        for(int i=2;i<n;i++){
            a[i] = a[i-1]+a[i-2];
        }
        return a[n-1];
    }
}
