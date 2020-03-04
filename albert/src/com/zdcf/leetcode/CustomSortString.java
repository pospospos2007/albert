package com.zdcf.leetcode;
//791. Custom Sort String
//S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
//
//S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
//
//Return any permutation of T (as a string) that satisfies this property.
//
//Example :
//Input: 
//S = "cba"
//T = "abcd"
//Output: "cbad"
//Explanation: 
//"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
//Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
// 
//
//Note:
//
//S has length at most 26, and no character is repeated in S.
//T has length at most 200.
//S and T consist of lowercase letters only.
//按照S的字母出现的顺序来排列T中的字符，没出现在S中的随便排。这个主要就是用个HashMap装T中的字符，计算他出现的次数，然后遍历S按照S的顺序组装新的字符串
import java.util.HashMap;
import java.util.Map;

public class CustomSortString {
	public String customSortString(String S, String T) {
        StringBuilder result = new StringBuilder();
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for(int i=0;i<T.length();i++){
            if(map.containsKey(T.charAt(i))){
                map.put(T.charAt(i),map.get(T.charAt(i))+1);
            }else{
                map.put(T.charAt(i),1);
            }
        }
        for(int i=0;i<S.length();i++){
            if(map.containsKey(S.charAt(i))){
                for(int j=0;j<map.get(S.charAt(i));j++){
                    result.append(S.charAt(i));
                }
                
            }
            map.remove(S.charAt(i));
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {  
           for(int j=0;j<entry.getValue();j++){
                result.append(entry.getKey());
            }
            
        }  
        return result.toString();
        
    }
}
