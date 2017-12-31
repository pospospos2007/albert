package com.zdcf.leetcode;
//686. Repeated String Match
//
//Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
//
//For example, with A = "abcd" and B = "cdabcdab".
//
//Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
//
//Note:
//The length of A and B will be between 1 and 10000.
public class RepeatedStringMatch {
	public int repeatedStringMatch(String A, String B) {
        int count = 1;
        int temp = B.length()/A.length();
        StringBuilder newA = new StringBuilder(A);
        if(A.length()<B.length()){
            if(B.length()%A.length()!=0){
                count =count+temp;
                temp = temp+1;
            }else{
                count = count+temp-1;
            }
        }
        while(temp>1){
            newA.append(A);
            temp--;
        }
        if(newA.toString().contains(B)){
            return count;
        }else{
             newA.append(A);
            count++;
             if(newA.toString().contains(B)){
                 return count;
             }else{
                 return -1;
             }
        }
        
    }
}
