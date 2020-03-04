package com.zdcf.leetcode;

import java.util.HashMap;
import java.util.Map;
//202. Happy Number
//Write an algorithm to determine if a number is "happy".
//
//A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
//
//Example: 19 is a happy number
//
//12 + 92 = 82
//82 + 22 = 68
//62 + 82 = 100
//12 + 02 + 02 = 1

public class HappyNumber {
	 public boolean isHappy(int n) {
	        if(n<1) return false;
	        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	        int nextSum = n;
	        while(nextSum!=1){
	            nextSum = Sum(nextSum) ;
	            if(map.containsKey(nextSum)){
	                return false;
	            }else if(nextSum==1){
	                break;
	            }else{
	                map.put(nextSum,1);
	            }
	           
	        }
	        return true;
	    }
	    public Integer Sum(int n){
	        int sum=0,temp;
	        while(n>0){
	            temp = n%10;
	            sum += temp*temp;
	            n = n/10;
	        }
	        return sum;
	    }
}
