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

			
}
