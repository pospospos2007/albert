package com.zdcf.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//500. Keyboard Row
//Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
//
//
//American keyboard
//
//
//Example 1:
//Input: ["Hello", "Alaska", "Dad", "Peace"]
//Output: ["Alaska", "Dad"]
//Note:
//You may use one character in the keyboard more than once.
//You may assume the input string will only contain letters of alphabet.
//给定一个个字符串列表，返回在键盘上能在一行输入的字符串，这道题最重要的是要将Map好好利用起来，将字母表倒进Map，value是行数，有个这个行数就好比较到底是不是在一行上的字符串了
public class KeyboardRow {
	 public static String[] findWords(String[] words) {
	        String[] firstRow = new String[]{"q","w","e","r","t","y","u","i","o","p"};
	        String[] secondRow = new String[]{"a","s","d","f","g","h","j","k","l"};
	        String[] thirdRow = new String[]{"z","x","c","v","b","n","m"};
	        List<String> list  = new ArrayList<String>();
	        HashMap<String,String> map = new HashMap<String,String>();
	        for(int i=0;i<firstRow.length;i++){
	            map.put(firstRow[i],"1");
	        }
	        for(int i=0;i<secondRow.length;i++){
	            map.put(secondRow[i],"2");
	        }
	        for(int i=0;i<thirdRow.length;i++){
	            map.put(thirdRow[i],"3");
	        }
	        for(int i=0;i<words.length;i++){
	            boolean flag = true;
	            String row = "0";
	            for(int j=0;j<words[i].length();j++){
	                if(row.equals("0")){
	                    row=map.get((words[i].charAt(j)+"").toLowerCase());
	                }else if(map.get((words[i].charAt(j)+"").toLowerCase()).equals(row)){
	                    flag = true;
	                }else{
	                    flag = false;
	                    break;
	                }
	            }
	            if(flag){
	                list.add(words[i]);
	            }
	        }
	        String[] result = new String[list.size()];
	        for(int i=0;i<list.size();i++){
	            result[i] = list.get(i);
	        }
	        return result;
	    }
	 public static void main(String[] args) {
		 String[] words = new String[]{"www","dsadd","dcxsd"};
		 System.out.println( findWords(words));
		
	}

}
