package com.zdcf.leetcode;
//238. Product of Array Except Self
//Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
//
//Solve it without division and in O(n).
//
//For example, given [1,2,3,4], return [24,12,8,6].
//
//Follow up:
//Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
public class ProductofArrayExceptSelf {

	 public int[] productExceptSelf(int[] nums) {
	        int[] leftStartArray = new int[nums.length];
	        int[] rightStartArray = new int[nums.length];
	        int[] rs = new int[nums.length];
	        leftStartArray[0] =1;
	        rightStartArray[nums.length-1] =1;
	        
	        for(int i=1;i<nums.length;i++){
	            leftStartArray[i]  = leftStartArray[i-1]*nums[i-1];
	        }
	        for(int j=nums.length-2;j>=0;j--){
	            rightStartArray[j] = rightStartArray[j+1]*nums[j+1];
	        }
	        for(int k=0;k<nums.length;k++){
	            rs[k] = leftStartArray[k]*rightStartArray[k];
	        }
	        return rs;
	    }
}
