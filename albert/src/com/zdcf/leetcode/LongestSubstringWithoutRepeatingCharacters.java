package com.zdcf.leetcode;

import java.util.HashSet;

//3. Longest Substring Without Repeating Characters
//Given a string, find the length of the longest substring without repeating characters.
//
//        Example 1:
//
//        Input: "abcabcbb"
//        Output: 3
//        Explanation: The answer is "abc", with the length of 3.
//        Example 2:
//
//        Input: "bbbbb"
//        Output: 1
//        Explanation: The answer is "b", with the length of 1.
//        Example 3:
//
//        Input: "pwwkew"
//        Output: 3
//        Explanation: The answer is "wke", with the length of 3.
//        Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
public class LongestSubstringWithoutRepeatingCharacters {


    public int lengthOfLongestSubstring(String s) {
        //把出现过的字符都放入set中，遇到set中没有的字符就加入set中并更新结果res，如果遇到重复的，则从左边开始删字符，直到删到重复的字符停止：
        int max = 0,left=0,right=0;
        HashSet<Character> set =new HashSet<Character>();

        while(right<s.length()){
            if(!set.contains(s.charAt(right))){
                set.add(s.charAt(right++));
                max = Math.max(max,set.size());
            }else{
                set.remove(s.charAt(left++));
            }
        }
        return max;

    }

}
