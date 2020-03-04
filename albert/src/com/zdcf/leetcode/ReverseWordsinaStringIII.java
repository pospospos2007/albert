package com.zdcf.leetcode;
//557. Reverse Words in a String III
//Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
//
//Example 1:
//Input: "Let's take LeetCode contest"
//Output: "s'teL ekat edoCteeL tsetnoc"
//Note: In the string, each word is separated by single space and there will not be any extra space in the string.
//两种接发，一种时间复杂度O(n2),O(n)，最好的是整个先翻转然后各个单词顺序再翻转
public class ReverseWordsinaStringIII {
	public String reverseWords(String s) {
//      if(s.equals("")||s==null){
//          return s;
//      }
//      String rs = "";
//      String[] temp = s.split(" ");
//      for(int i=0;i<temp.length;i++){
//          for(int j=temp[i].length();j>0;j--){
//              rs = rs+temp[i].charAt(j-1);
//          }
//          if(i+1!=temp.length){
//              rs = rs+" ";
//          }
         
//      }
//      return rs;
     StringBuilder rs = new StringBuilder();
     for(int i=s.length()-1;i>=0;i--){
         rs.append(s.charAt(i));
     }
     String[] temp = rs.toString().split(" ");
     StringBuilder result =new StringBuilder();
     for(int i=temp.length-1;i>=0;i--){
         result.append(temp[i]);
         if(i!=0){
             result.append(" ");
         }
     }
     return result.toString();
         
 }

}
