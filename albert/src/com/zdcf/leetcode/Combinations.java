package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

//77. Combinations
//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
//
//        Example:
//
//        Input: n = 4, k = 2
//        Output:
//        [
//        [2,4],
//        [3,4],
//        [2,3],
//        [1,2],
//        [1,3],
//        [1,4],
//        ]
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        helper(result,temp,n,k,1);
        return result;

    }
    private void helper ( List<List<Integer>> result,List<Integer> temp,int n,int k,int start){
        if(k==temp.size()){
            //注意需要New！
            result.add(new ArrayList<Integer>(temp));
            return ;
        }
        for(int i=start;i<=n;i++){
            temp.add(i);
            helper(result,temp,n,k,i+1);
            temp.remove(temp.size()-1);
        }
    }
}
