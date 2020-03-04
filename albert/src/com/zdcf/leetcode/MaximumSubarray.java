package com.zdcf.leetcode;
//53. Maximum Subarray
//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//
//For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
//the contiguous subarray [4,-1,2,1] has the largest sum = 6.
//
//
//More practice:
//If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
public class MaximumSubarray {
	public int maxSubArray(int[] nums) {
        if(null==nums||nums.length<1){
            return 0;
        }
        int[] sum = new int [nums.length];
        int max = nums[0];
        sum[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            sum[i]=Math.max(sum[i-1]+nums[i],nums[i]);
            max = Math.max(sum[i],max);
        }
        return max;
    }
}
