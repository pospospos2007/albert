package com.zdcf.leetcode;

import java.util.Arrays;

//Leetcode 16. 3Sum Closest
//16. 3Sum Closest
//        Medium
//
//        Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//        Example:
//
//        Given array nums = [-1, 2, 1, -4], and target = 1.
//
//        The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
public class ThreeSumClosest {

    //思路用滑动窗口，先排好序，然后用2个指针0，start ,end这样滑动
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0]+nums[1]+nums[nums.length-1];
        for(int i=0;i<nums.length-2;i++){
            int start = i+1;
            int end = nums.length-1;
            while(start<end){
                int sum = nums[i]+nums[start]+nums[end];
                if(Math.abs(sum-target)<Math.abs(res-target)){
                    res =sum;
                }
                if(sum>target){
                    end--;
                }else{
                    start++;
                }
            }
        }
        return res;
    }


}
