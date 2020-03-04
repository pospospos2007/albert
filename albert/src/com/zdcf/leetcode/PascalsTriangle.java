package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for(int i=0;i<numRows;i++){
            List<Integer> list = new ArrayList<Integer>();
            for(int j=0;j<i+1;j++){
                if(j==0){
                    list.add(1);
                }else if(result.get(i-1).size()==j){
                    list.add(result.get(i-1).get(j-1)+0);
                }else{
                    list.add(result.get(i-1).get(j-1)+result.get(i-1).get(j));
                }
            }
            result.add(list);
        }
        return result;
        
    }
	public static void main(String[] args) {
		PascalsTriangle p = new PascalsTriangle();
		System.out.println(p.generate(5));
	}
}
