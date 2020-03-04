package com.zdcf.leetcode;
//5. Longest Palindromic Substring
//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//        Example 1:
//
//        Input: "babad"
//        Output: "bab"
//        Note: "aba" is also a valid answer.
//        Example 2:
//
//        Input: "cbbd"
//        Output: "bb"
public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if(s==null||s.length()<2){
            return s;
        }
        int i=0;
        String maxStr = Character.toString(s.charAt(0));
        while(i<s.length()){
            String oddStr = helper(s,i,i);
            String evenStr = helper(s,i,i+1);
            if(maxStr.length()<oddStr.length()){
                maxStr = oddStr;
            }
            if(maxStr.length()<evenStr.length()){
                maxStr = evenStr;
            }
            i++;
        }
        return maxStr;
    }
    private static String helper(String s , int i ,int j){
        while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
//         beginIndex -- 起始索引（包括）, 索引从 0 开始。

// endIndex -- 结束索引（不包括
        if(i==j){
            return s.substring(i,j+1);
        }else{
            return s.substring(i+1,j);
        }

    }

    public static void main(String[] args) {
        String s = "abbass";
        System.out.println("test");
        System.out.println(longestPalindrome(s));
    }
}
