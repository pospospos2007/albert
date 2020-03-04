package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//15. 3Sum
//Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
//Note:
//
//The solution set must not contain duplicate triplets.
//
//Example:
//
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//给定一组数组，返回和为0的三个数字的集合组合，思路是先快排，然后使用3个指针来遍历数组，第一个从0到length-3，剩下两个指针分别从剩余集合的两端遍历至中央，
//如何sum大了就high指针往左一一点，小了low指针往右边移动，等于了的话注意要使用Hashset来保存不重复的集合
//时间复杂度:   快排O（nlogn）+  嵌套循环O（n^2） =  O(n^2)
public class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
	       
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        if(nums==null||nums.length<3){
            return result;
        }
        Arrays.sort(nums);
        int low,high,sum=0;
        for(int i=0;i<=nums.length-3;i++){
            low = i+1;
            high = nums.length-1;
            while(low<high){
                sum = nums[i]+nums[low]+nums[high];
                if(sum==0){
                    ArrayList<Integer> unit = new ArrayList<Integer>();
                    unit.add(nums[i]);
                    unit.add(nums[low]);
                    unit.add(nums[high]);
                    if(!set.contains(unit)){
                        set.add(unit);
                        result.add(unit);
                    }
                    high--;
                    low++;
                }else if(sum>0){
                     high--;
                }else if(sum<0){
                    low++;
                }
            }
        }
        return result;
        
    }
}
