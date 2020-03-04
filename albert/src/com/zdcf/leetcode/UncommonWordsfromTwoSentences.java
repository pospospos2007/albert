package com.zdcf.leetcode;

import java.util.HashSet;

//884. Uncommon Words from Two Sentences
//We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
//
//A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
//
//Return a list of all uncommon words. 
//
//You may return the list in any order.
//
// 
//
//Example 1:
//
//Input: A = "this apple is sweet", B = "this apple is sour"
//Output: ["sweet","sour"]
//Example 2:
//
//Input: A = "apple apple", B = "banana"
//Output: ["banana"]
// 
//
//Note:
//
//0 <= A.length <= 200
//0 <= B.length <= 200
//A and B both contain only spaces and lowercase letters.

//将存在一个String中的单词而不存在另一个String的单词筛选出来，思路就是建二哥set，一个存所有的单词，另一个存重复的单词，去掉第二个重复的就是满足条件的单词了
public class UncommonWordsfromTwoSentences {
	 public String[] uncommonFromSentences(String A, String B) {
	        String[] tempA = A.split(" ");
	        String[] tempB = B.split(" ");
	        HashSet<String> setNotDup =  new  HashSet<String>();
	        HashSet<String> setDup =  new  HashSet<String>();
	        for(int i=0;i<tempA.length;i++){
	            if(!setNotDup.contains(tempA[i])){
	                setNotDup.add(tempA[i]);
	            }else{
	                setDup.add(tempA[i]);
	            }
	        }
	         for(int i=0;i<tempB.length;i++){
	            if(!setNotDup.contains(tempB[i])){
	                setNotDup.add(tempB[i]);
	            }else{
	                setDup.add(tempB[i]);
	            }
	        }
	        for(String s:setDup){
	            if(setNotDup.contains(s)){
	                setNotDup.remove(s);
	            }
	        }
	        return setNotDup.toArray(new String[]{});
	    }
}
