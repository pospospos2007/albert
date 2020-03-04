package com.zdcf.leetcode;
//852. Peak Index in a Mountain Array
//Let's call an array A a mountain if the following properties hold:
//
//A.length >= 3
//There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
//Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
//
//Example 1:
//
//Input: [0,1,0]
//Output: 1
//Example 2:
//
//Input: [0,2,1,0]
//Output: 1
//Note:
//
//3 <= A.length <= 10000
//0 <= A[i] <= 10^6
//A is a mountain, as defined above.
//就是找峰点，如果左边右边分别2个数字都比他小，就是峰点
public class PeakIndexinaMountainArray {

	public int peakIndexInMountainArray(int[] A) {
        for(int i=0;i<A.length;i++){
            boolean isMountain = true;
            if(i>=0){
                for(int j=i-1;j>=0;j--){
                    if(A[i]<A[j]){
                        isMountain = false;
                        break;
                    }
                }
            }
            if(i<A.length-1){
                for(int k=i+1;k<A.length;k++){
                    if(A[k]>A[i]){
                        isMountain = false;
                        break;
                    }
                }
            }
            if(isMountain){
                return i;
            }
        }
        return 0;
    }
	
}
