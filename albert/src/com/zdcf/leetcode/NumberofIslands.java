package com.zdcf.leetcode;
//200. Number of Islands
//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//        Example 1:
//
//        Input:
//        11110
//        11010
//        11000
//        00000
//
//        Output: 1
//        Example 2:
//
//        Input:
//        11000
//        11000
//        00100
//        00011
//
//        Output: 3
//        Accepted
//        398,482
//        Submissions
//        939,286
public class NumberofIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    ++count;
                    dfs(grid,count,i,j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid,int count , int i,int j){
        if(grid[i][j]=='1'){
            grid[i][j]=0;
        }else{
            return ;
        }

        if(j>0){
            dfs(grid,count,i,j-1);
        }
        if(i>0){
            dfs(grid,count,i-1,j);
        }
        if(i<grid.length-1){
            dfs(grid,count,i+1,j);
        }
        if(j<grid[0].length-1){
            dfs(grid,count,i,j+1);
        }
    }
}
