package com.zdcf.leetcode;

import java.util.HashMap;
import java.util.Map;

//169. Majority Element
//Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
//
//You may assume that the array is non-empty and the majority element always exist in the array.
public class MajorityElement {
	 public int majorityElement(int[] nums) {
	        int max = nums.length/2+1;
	        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	        for(int i=0;i<nums.length;i++){
	            if(map.containsKey(nums[i])){
	                if((map.get(nums[i])+1)==max){
	                    return nums[i];
	                }
	                map.put(nums[i],map.get(nums[i])+1);
	                
	            }else{
	                if(1==max){
	                    return nums[i];
	                }
	                 map.put(nums[i],1);
	            }
	           
	        }
	        return 0;
	    }
}
