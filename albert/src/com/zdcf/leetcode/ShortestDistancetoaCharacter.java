package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.List;

//821.Shortest Distance to a Character
//Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
//
//Example 1:
//
//Input: S = "loveleetcode", C = 'e'
//Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
// 
//
//Note:
//
//S string length is in [1, 10000].
//C is a single character, and guaranteed to be in string S.
//All letters in S and C are lowercase.
//给定字符串S，和字符C，返回S中所有字符距离C字符的距离的数组。解法是先存储所有的有C的字符的下标，然后循环S一一计算下标差的最小的那个绝对值
public class ShortestDistancetoaCharacter {
	public static int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        List<Integer> zeroIndex = new ArrayList<Integer>();
        for(int i=0;i<S.length();i++){
            if(S.charAt(i)==C)
                zeroIndex.add(i);
        }
        for(int i=0;i<S.length();i++){
            for(int j=0,temp=0;j<zeroIndex.size();j++){
                if(zeroIndex.get(j)==i){
                    result[i] =0;
                    break;
                }else{
                    temp = Math.abs(i-zeroIndex.get(j));
                    if(result[i]==0||temp<result[i]){
                        result[i] = temp;
                    }
                }
            }
        }
        return result;
            
        
    }
	public static void main(String[] args) {
		System.out.println(shortestToChar("loveleetcode",'e'));
		
	}
}
