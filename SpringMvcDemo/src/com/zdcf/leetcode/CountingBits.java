package com.zdcf.leetcode;
//Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
//
//Example:
//For num = 5 you should return [0,1,1,2,1,2].
//
//Follow up:
//
//It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
//Space complexity should be O(n).
//Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
public class CountingBits {
	// 0    0000    0
	// -------------
	// 1    0001    1
	// -------------
	// 2    0010    1            
	// 3    0011    2
	// -------------
	// 4    0100    1
	// 5    0101    2
	// 6    0110    2
	// 7    0111    3
	// -------------
	// 8    1000    1
	// 9    1001    2
	// 10   1010    2
	// 11   1011    3
	// 12   1100    2
	// 13   1101    3
	// 14   1110    3
	// 15   1111    4
	public static void main(String[] args) {
		countBits(8);
	}
	public static int[] countBits(int num) {
        int[] rs = new int[num+1];
        int rank;
        int lastRank;
        int startIndex;
        int remain;
        int currntAmount;
        int maxEdge;
        for(int i=0;i<=num;i++){
            if(i<2){
               rs[i]=i; 
            }else if (i>1&&i<4){
                rs[i] = i-1;
            }else{
                rank = mylog2(i);
//                if(rank%2==0){
//                    lastRank = rank-1;
                    startIndex  = (int)Math.pow(2.0,(double)rank);
//                    maxEdge = (int)Math.pow((double)rank+1,2.0);
//                }else{
//                    lastRank = rank-2;
//                    startIndex  = (int)Math.pow((double)rank-1,2.0);
//                    maxEdge = (int)Math.pow((double)rank,2.0);
//                }
                remain = i-startIndex+1;
                if(remain<(startIndex/2)){
                    rs[i] = rs[startIndex/2+remain-1];
                }else{
                     rs[i] = rs[startIndex/2+remain-startIndex/2-1]+1;
                }
            }
            
        }
        return rs;
        
    }
    private static int mylog2(int val){
        
        return (int)(Math.log((double)val)/Math.log(2.0));
        
    }
}
