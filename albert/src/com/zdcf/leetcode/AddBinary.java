package com.zdcf.leetcode;
//67. Add Binary
//Given two binary strings, return their sum (also a binary string).
//
//        The input strings are both non-empty and contains only characters 1 or 0.
//
//        Example 1:
//
//        Input: a = "11", b = "1"
//        Output: "100"
//        Example 2:
//
//        Input: a = "1010", b = "1011"
//        Output: "10101"
public class AddBinary {

    public String addBinary(String a, String b) {
        int carry = 0;
        Integer max = Math.max(a.length(),b.length());
        StringBuilder result=new StringBuilder();
        int tempA = 0;
        int tempB = 0;
        for(int i=1;i<=max;i++){
            tempA = a.length()>=i?a.charAt(a.length()-i)-'0':0;
            tempB = b.length()>=i?b.charAt(b.length()-i)-'0':0;
            result.insert( 0 , (tempA+tempB+carry)%2);
            carry = tempA+tempB+carry>1?1:0;
        }
        if(carry ==1){
            result.insert( 0 , carry);
        }

        return result.toString();
    }
}
