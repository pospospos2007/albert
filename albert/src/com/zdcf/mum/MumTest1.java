package com.zdcf.mum;

import java.util.HashMap;
import java.util.Map;

public class MumTest1 {

	public static void main(String[] args) {
//		Test1:
		System.out.println(primeCount(10,30));
//		System.out.println(primeCount(11,29));
//		System.out.println(primeCount(20,22));
//		System.out.println(primeCount(1,1));
//		System.out.println(primeCount(5,5));
//		System.out.println(primeCount(6,2));
//		System.out.println(primeCount(-10,6));
//		Test2:
//		int[] a = {2,1,1};
//		int[] b = {2,1,1,4,-1,-1};
//		int[] c = {6,2,4,2,2,2,1,5,0,0};
//		int[] d = {18,9,10,6,6,6};
//		int[] e = {-6,-3,-3,8,-5,-4};
//		int[] f = {0,0,0,0,0,0,0,0,0,0,1,1,1,-2,-1};
//		int[] g = {3,1,2,3,0};
//		System.out.println(isMadhavArray(a));
//		System.out.println(isMadhavArray(b));
//		System.out.println(isMadhavArray(c));
//		System.out.println(isMadhavArray(e));
//		System.out.println(isMadhavArray(f));
//		System.out.println(isMadhavArray(g));
//		System.out.println(Integer.MAX_VALUE);
	}	
	
//Test1:
	public static int primeCount(int start, int end){
		if(start>end||end<2){
			return 0;
		}
		if(start<2) start=2;
		int count = 0;
		boolean[] array =new boolean[end+1];
		for(int i=2;i*i<=end;i++){
			if(!array[i]){
				for(int j=i;i*j<=end;j++){
					array[i*j]=true;
				}
			}
		}
		for(int k=start;k<=end;k++){
			if(!array[k]) {
				System.out.println("prime:"+k);
				count++;
			}
		}
		return count;
	}
	

	
}
