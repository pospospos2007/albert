package com.zdcf.leetcode;
//14. Longest Common Prefix   前缀记住了，是前缀！
//Write a function to find the longest common prefix string amongst an array of strings.
public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
        if(null==strs||strs.length==0){
           return ""; 
        }
        String minStr = strs[0];
        int minStrlength = strs[0].length();
        int smallIndex = 0;
        int commonLength = 0;
        String commonStr ="";
        for(int i=0;i<strs.length;i++){
            if(minStr.length()>strs[i].length()){
                minStr = strs[i];
                minStrlength = strs[i].length();
                smallIndex = i;
            }
        }
        commonLength = minStrlength;
        String longestCommonPrefix;
        boolean isSame = true;
        int index = -1;
        for(int j=0;j<commonLength;j++){
            isSame = true;
            for(int i=0;i<strs.length;i++){
                if(strs[i].charAt(j)!=minStr.charAt(j)){
                    isSame = false;
                    break;
                }

            }
            if(isSame){
                index++;
            }else{
                break;
            }
                
        }
        return index==-1 ? "" : minStr.substring(0,index+1);
        
    }
}
