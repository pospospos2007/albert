package com.zdcf.leetcode;
//819. Most Common Word

import java.util.HashMap;
import java.util.HashSet;
//Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
//
//Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
//
//Example:
//Input: 
//paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//banned = ["hit"]
//Output: "ball"
//Explanation: 
//"hit" occurs 3 times, but it is a banned word.
//"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
//Note that words in the paragraph are not case sensitive,
//that punctuation is ignored (even if adjacent to words, such as "ball,"), 
//and that "hit" isn't the answer even though it occurs more because it is banned.
// 
//
//Note:
//
//1 <= paragraph.length <= 1000.
//1 <= banned.length <= 100.
//1 <= banned[i].length <= 10.
//The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
//paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
//Different words in paragraph are always separated by a space.
//There are no hyphens or hyphenated words.
//Words only consist of letters, never apostrophes or other punctuation symbols.
//给定一句话和一组被ban的字符串数组，求在这句话中出现次数最多的没有被ban的词，主要用到regex、Hashset和Hahsmap，利用hashset数据结构能很容易判断是否存在被banned字符
//另外切割字符的时候注意regex会造成切割出“”的字符，需要在判断的时候舍去
public class MostCommonWord {
	
	public  static String mostCommonWord(String paragraph, String[] banned) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        HashSet<String> bannedSet = new HashSet<String>();
        int max = 0;
        String result="";
        String[] array = paragraph.toLowerCase().split(" |!|\\?|'|,|;|\\.");
       
        for(String s:banned){
            bannedSet.add(s);
        }
        for(int i=0;i<array.length;i++){
            if(!bannedSet.contains(array[i])&&!array[i].equals("")){
                map.put(array[i],map.getOrDefault(array[i],0)+1);
                if(max<map.get(array[i])){
                    max = map.get(array[i]);
                    result = array[i];
                }
            }
        }
                                     
        return result;
    }
	 public static void main(String[] args) {
		String s = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] i = {"hit"};
		System.out.println(mostCommonWord(s,i));
	}
	 

}
