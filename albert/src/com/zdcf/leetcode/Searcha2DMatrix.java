package com.zdcf.leetcode;
//74. Search a 2D Matrix
//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//        Integers in each row are sorted from left to right.
//        The first integer of each row is greater than the last integer of the previous row.
//        Example 1:
//
//        Input:
//        matrix = [
//        [1,   3,  5,  7],
//        [10, 11, 16, 20],
//        [23, 30, 34, 50]
//        ]
//        target = 3
//        Output: true
//        Example 2:
//
//        Input:
//        matrix = [
//        [1,   3,  5,  7],
//        [10, 11, 16, 20],
//        [23, 30, 34, 50]
//        ]
//        target = 13
//        Output: false
public class Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        //注意二维数组的判空
        if(matrix==null||matrix.length==0||matrix[0].length==0){
            return false;
        }
        int low =0;
        int high  = matrix.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(matrix[mid][0]==target){
                return true;
            }else if(matrix[mid][0]>target){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        if(high<0){
            return false;
        }
        int row = high;//因为减了1，所以一定比target小
        low =0;
        high  = matrix[0].length-1;
        while(low<=high){
            int mid  = (low+high)/2;
            if(matrix[row][mid]==target){
                return true;
            }else if(matrix[row][mid]>target){
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return false;
    }
}
