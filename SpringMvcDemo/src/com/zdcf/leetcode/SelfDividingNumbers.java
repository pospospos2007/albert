package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

//728. Self Dividing Numbers
//A self-dividing number is a number that is divisible by every digit it contains.
//
//For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
//
//Also, a self-dividing number is not allowed to contain the digit zero.
//
//Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
//
//Example 1:
//Input: 
//left = 1, right = 22
//Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
//Note:
//
//The boundaries of each input argument are 1 <= left <= right <= 10000.
public class SelfDividingNumbers {
	public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<Integer>();
        int suffix;
        boolean isSelfDivied =true;
        int temp;
        for(int i=left;i<=right;i++){
            temp = i;
            while(temp!=0){
                suffix = temp%10;
                if(suffix==0){//注意边界，数字中不允许有0
                    isSelfDivied = false;
                    break;
                }
                temp = temp/10;
                if(i%suffix==0){
                    isSelfDivied = true;
                }else{
                    isSelfDivied = false;
                    break;
                }
                
            }
            if(isSelfDivied){
                res.add(i);
            }
        }
        return res;
    }
}
