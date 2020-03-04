package com.zdcf.leetcode;
//695. Max Area of Island
//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
//Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
//
//Example 1:
//[[0,0,1,0,0,0,0,1,0,0,0,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,1,1,0,1,0,0,0,0,0,0,0,0],
// [0,1,0,0,1,1,0,0,1,0,1,0,0],
// [0,1,0,0,1,1,0,0,1,1,1,0,0],
// [0,0,0,0,0,0,0,0,0,0,1,0,0],
// [0,0,0,0,0,0,0,1,1,1,0,0,0],
// [0,0,0,0,0,0,0,1,1,0,0,0,0]]
//Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
//Example 2:
//[[0,0,0,0,0,0,0,0]]
//Given the above grid, return 0.
//Note: The length of each dimension in the given grid does not exceed 50.
public class MaxAreaofIsland {
	public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int temp;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]!=0){
                    temp = calArea(grid,i,j);
                    maxArea = Math.max(temp,maxArea);
                }
                
            }
        }
        return maxArea;
    }
    private int calArea(int[][] grid,int i,int j){
        int count = 1;
        //empty current area
        grid[i][j] = 0;
        //top
        if(i!=0&&0!=grid[i-1][j]){
            count = count+calArea(grid,i-1,j);
        }
        //left
        if(j!=0&&0!=grid[i][j-1]){
            count = count+calArea(grid,i,j-1);
        }
        //right
        if(j!=grid[i].length-1&&0!=grid[i][j+1]){
            count = count+calArea(grid,i,j+1);
        }
        //bottom
        if(i!=grid.length-1&&0!=grid[i+1][j]){
            count = count+calArea(grid,i+1,j);
        }
        return count;
        
    } 

}
