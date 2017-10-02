package com.zdcf.leetcode;

import java.lang.reflect.Array;
//204. Count Primes
//Description:
//
//Count the number of prime numbers less than a non-negative number, n.
//
//Credits:
//Special thanks to @mithmatt for adding this problem and creating all test cases.
/**
 * 解题思路：埃拉托斯特尼筛法Sieve of Eratosthenes
 * @author Albert
 *
 */

public class CountPrimes {
    public static int countPrimes(int n) {
        if(n<2){
            return 0;
        }
        
        boolean[] array = new boolean[n];
        int count = 0;
        for(int i = 2;i*i<n;i++){
            if(!array[i]){
                for(int j=i;j*i<n;j++){
                    array[i*j]=true;
                }
            }
        }
        for(int k=2;k<n;k++){
            if(array[k]==false){
                count++;
            }
        }
        return count;
    }
	public static void main(String[] args) {
//		System.out.println(countPrimes(10));
		System.out.println(primeCount(10,30));
		System.out.println(primeCount(11,29));
		System.out.println(primeCount(20,22));
		System.out.println(primeCount(1,1));
		System.out.println(primeCount(5,5));
		System.out.println(primeCount(6,2));
		System.out.println(primeCount(-10,6));
	}	
	
//MumTest1:
	public static int primeCount(int start, int end){
		if(start>end||end<2){
			return 0;
		}
		if(start<2) start=2;
		int count = 0;
		boolean[] array =new boolean[end+1];
		for(int i=2;i*i<=end;i++){
			if(!array[i]){
				for(int j=i;i*j<=end;j++){
					array[i*j]=true;
				}
			}
		}
		for(int k=start;k<=end;k++){
			if(!array[k]) count++;
		}
		return count;
	}
	
	
			
}
