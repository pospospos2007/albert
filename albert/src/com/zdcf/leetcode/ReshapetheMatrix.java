package com.zdcf.leetcode;
//566. Reshape the Matrix
//In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
//
//You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
//
//The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
//
//If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
//
//Example 1:
//Input: 
//nums = 
//[[1,2],
// [3,4]]
//r = 1, c = 4
//Output: 
//[[1,2,3,4]]
//Explanation:
//The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
//Example 2:
//Input: 
//nums = 
//[[1,2],
// [3,4]]
//r = 2, c = 4
//Output: 
//[[1,2],
// [3,4]]
//Explanation:
//There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
//Note:
//The height and width of the given matrix is in range [1, 100].
//The given r and c are all positive.
//给定一个矩阵，按照给定的行数和列数重新排列，如何总和不对返回原来的阵列。解决的死方法是依照顺序一一排列到新的矩阵中
public class ReshapetheMatrix {
	public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(r*c!=nums.length*nums[0].length){
            return nums;
        }
        int result[][] = new int[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                result[i][j]  =  get((j+1)+i*c,nums);
            }
        }
        return result;
    }
    public int get(int index,int[][] nums){
        int count =0;
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<nums[i].length;j++){
                count++;
                if(count==index){
                    return nums[i][j];
                }
            }
        }
        return 0;
    }
}
