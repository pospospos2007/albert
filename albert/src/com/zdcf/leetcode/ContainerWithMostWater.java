package com.zdcf.leetcode;
//11. Container With Most Water

//Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
//        Note: You may not slant the container and n is at least 2.
//
//
//
//
//
//        The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//
//
//
//        Example:
//
//        Input: [1,8,6,2,5,4,8,3,7]
//        Output: 49
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        //用贪心算法，取start和end索引，如果左边高度小，+1，看看+1的是不是面积更大，因为只有比他高的才有可能面积更大
        int start =0;
        int end = height.length;
        int max = 0;

        if(height.length<2){
            return max;
        }
        while(start<end){
            if(height[start]<height[end-1]){
                max = Math.max(max,height[start]*(end-1-start));
                start++;
            }else{
                max = Math.max(max,height[end-1]*(end-1-start));
                end--;
            }
        }
        return max;
    }
}
