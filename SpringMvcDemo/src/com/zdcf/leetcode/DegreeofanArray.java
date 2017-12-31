package com.zdcf.leetcode;

import java.util.HashMap;
import java.util.Map;

//697. Degree of an Array
//Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
//
//Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
//
//Example 1:
//Input: [1, 2, 2, 3, 1]
//Output: 2
//Explanation: 
//The input array has a degree of 2 because both elements 1 and 2 appear twice.
//Of the subarrays that have the same degree:
//[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
//The shortest length is 2. So return 2.
//Example 2:
//Input: [1,2,2,3,1,4,2]
//Output: 6
//Note:
//
//nums.length will be between 1 and 50,000.
//nums[i] will be an integer between 0 and 49,999.
public class DegreeofanArray {
	
	 public int findShortestSubArray(int[] nums) {
	        if(nums.length==1){
	            return nums.length;
	        }
	        int shortestLength = Integer.MAX_VALUE;
	        int maxAppearFreq = 0;
	        int tempNum;
	        Map<Integer,Integer> numMap = new HashMap<Integer,Integer>();
	        Map<Integer,Integer> startIndexMap = new HashMap<Integer,Integer>();
	        Map<Integer,Integer> endIndexMap = new HashMap<Integer,Integer>();
	        for(int i=0;i<nums.length;i++){
	            if(numMap.containsKey(nums[i])){
	                tempNum = numMap.get(nums[i]) +1;
	                numMap.put(nums[i],tempNum);
	                endIndexMap.put(nums[i],i);
	                
	            }else{
	                numMap.put(nums[i],1);
	                startIndexMap.put(nums[i],i);
	                endIndexMap.put(nums[i],i);
	            }
	            if(numMap.get(nums[i])>=maxAppearFreq){
	                maxAppearFreq = numMap.get(nums[i]);
	            }
	        }
	        int temp;
	        int length;
	        for(Integer key:numMap.keySet()){
	            temp = numMap.get(key);
	            length = endIndexMap.get(key)-startIndexMap.get(key)+1;
	            if(temp==maxAppearFreq&&length<shortestLength){
	                shortestLength = length;
	            }
	        }
	        return shortestLength;
	        
	        
	    }
}
