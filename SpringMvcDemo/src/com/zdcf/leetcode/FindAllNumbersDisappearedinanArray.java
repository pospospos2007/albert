package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

//448. Find All Numbers Disappeared in an Array
//Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
//Find all the elements of [1, n] inclusive that do not appear in this array.
//
//Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
//
//Example:
//
//Input:
//[4,3,2,7,8,2,3,1]
//
//Output:
//[5,6]
public class FindAllNumbersDisappearedinanArray {
	public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> rs = new ArrayList<Integer>();
        if(nums==null||nums.length==0){
            return rs;
        }
        Integer[] s = new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
            s[nums[i]-1]=nums[i];
        }
        for(int j=0;j<s.length;j++){
           if(null==s[j]){
               rs.add(j+1);
           } 
        }
        
        return rs;
        
    }
}
