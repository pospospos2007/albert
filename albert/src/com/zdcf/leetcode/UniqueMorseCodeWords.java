package com.zdcf.leetcode;

import java.util.HashSet;
import java.util.Set;

//804. Unique Morse Code Words
//International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.
//
//For convenience, the full table for the 26 letters of the English alphabet is given below:
//
//[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
//Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, "cab" can be written as "-.-.-....-", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation of a word.
//
//Return the number of different transformations among all words we have.
//
//Example:
//Input: words = ["gin", "zen", "gig", "msg"]
//Output: 2
//Explanation: 
//The transformation of each word is:
//"gin" -> "--...-."
//"zen" -> "--...-."
//"gig" -> "--...--."
//"msg" -> "--...--."
//
//There are 2 different transformations, "--...-." and "--...--.".
// 
//
//Note:
//
//The length of words will be at most 100.
//Each words[i] will have length in range [1, 12].
//words[i] will only consist of lowercase letters.
//也就是求有多少个不同的连续字符组合
public class UniqueMorseCodeWords {
	
	//使用hashSet
	public int uniqueMorseRepresentations(String[] words) {
        String[] alphabet ={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        Set<String> result = new HashSet<String>();
        
        for(int i=0;i<words.length;i++){
            StringBuilder s = new StringBuilder();
            for(int j=0;j<words[i].length();j++){
                s.append(alphabet[words[i].charAt(j)-'a']);
            }
            result.add(s.toString());
        }
        return result.size();
    }
	

}
