package com.zdcf.leetcode;
//922. Sort Array By Parity II
//Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
//
//        Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
//
//        You may return any answer array that satisfies this condition.
//
//
//
//        Example 1:
//
//        Input: [4,2,5,7]
//        Output: [4,5,2,7]
//        Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
//
//
//        Note:
//
//        2 <= A.length <= 20000
//        A.length % 2 == 0
//        0 <= A[i] <= 1000
public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] A) {
        int result[] = new int[A.length];
        int oddPostionIndex=0;
        int evenPostionIndex=1;
        for(int i=0;i<A.length;i++){
            if(A[i]%2==0){
                result[oddPostionIndex]=A[i];
                oddPostionIndex+=2;
            }else{
                result[evenPostionIndex]=A[i];
                evenPostionIndex+=2;
            }
        }
        return result;
    }

}
