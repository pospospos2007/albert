package com.zdcf.leetcode;
//5048. Find Words That Can Be Formed by Characters
//You are given an array of strings words and a string chars.
//
//        A string is good if it can be formed by characters from chars (each character can only be used once).
//
//        Return the sum of lengths of all good strings in words.
//
//
//
//        Example 1:
//
//        Input: words = ["cat","bt","hat","tree"], chars = "atach"
//        Output: 6
//        Explanation:
//        The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
//        Example 2:
//
//        Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
//        Output: 10
//        Explanation:
//        The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
//
//
//        Note:
//
//        1 <= words.length <= 1000
//        1 <= words[i].length, chars.length <= 100
//        All strings contain lowercase English letters only.
public class FindWordsThatCanBeFormedbyCharacters {

    public static int countCharacters(String[] words, String chars) {
        int result = 0;
        int[] dict = new int[26];
        for(int i=0;i<chars.length();i++){
                dict[chars.charAt(i)-'a']++;
        }
        for(int j=0;j<words.length;j++){
            int[] temp  = dict.clone();
            for(int k=0;k<words[j].length();k++){
                if(temp[words[j].charAt(k)-'a']<1){
                    break;
                }else{
                    temp[words[j].charAt(k)-'a']--;
                }
                if(k==words[j].length()-1){
                    result+=words[j].length();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"cat","bt","hat","tree"};
        String chars = "atach";
        System.out.println(countCharacters(words,chars));
    }
}
