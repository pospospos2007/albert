package com.zdcf.leetcode;
//35. Search Insert Position
//Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//        You may assume no duplicates in the array.
//
//        Example 1:
//
//        Input: [1,3,5,6], 5
//        Output: 2
//        Example 2:
//
//        Input: [1,3,5,6], 2
//        Output: 1
//        Example 3:
//
//        Input: [1,3,5,6], 7
//        Output: 4
//        Example 4:
//
//        Input: [1,3,5,6], 0
//        Output: 0
public class SearchInsertPosition {


    //思路  二分查找，注意  mid = left+(right-left)/2;
    public int searchInsert(int[] nums, int target) {
        if(nums==null||nums.length<1){
            return 0;
        }
        int left = 0;
        int right = nums.length-1;
        int mid = 0;
        while(left<=right){
            mid = left+(right-left)/2;
            if(target>nums[mid]){
                left = mid+ 1;
            }else if(target>nums[mid]){
                return mid;
            }else{
                right = mid -1;
            }
        }
        return left;
    }
}
