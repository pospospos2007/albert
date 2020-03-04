package com.zdcf.leetcode;
//463. Island Perimeter
//You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
//
//Example:
//
//[[0,1,0,0],
// [1,1,1,0],
// [0,1,0,0],
// [1,1,0,0]]
//
//Answer: 16
//Explanation: The perimeter is the 16 yellow stripes in the image below:
//给定一个矩阵，求矩阵中1所形成的区域的周长。解法是判断每一个坐标分别上下左右有没有0的区域或者该点是边界，则周长加1
public class IslandPerimeter {

	public int islandPerimeter(int[][] grid) {
        int perimeter=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    //up
                    if(i==0){
                        perimeter++;
                    }else if(i!=0&&grid[i-1][j]==0){
                        perimeter++;
                    }
                    //down
                    if(i==grid.length-1){
                        perimeter++;
                    }else if(i!=grid.length-1&&grid[i+1][j]==0){
                        perimeter++;
                    }
                    //left
                    if(j==0){
                        perimeter++;
                    }else if(j!=0&&grid[i][j-1]==0){
                        perimeter++;
                    }
                    //right
                    if(j==grid[i].length-1){
                        perimeter++;
                    }else if(j!=grid[i].length-1&&grid[i][j+1]==0){
                        perimeter++;
                    }
                   
                    
                }
            }
        }
        return perimeter;   
    }
	
}
