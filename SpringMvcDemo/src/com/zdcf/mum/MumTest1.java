package com.zdcf.mum;

public class MumTest1 {

	public static void main(String[] args) {
//		Test1:
//		System.out.println(primeCount(10,30));
//		System.out.println(primeCount(11,29));
//		System.out.println(primeCount(20,22));
//		System.out.println(primeCount(1,1));
//		System.out.println(primeCount(5,5));
//		System.out.println(primeCount(6,2));
//		System.out.println(primeCount(-10,6));
//		Test2:
		int[] a = {2,1,1};
		int[] b = {2,1,1,4,-1,-1};
		int[] c = {6,2,4,2,2,2,1,5,0,0};
		int[] d = {18,9,10,6,6,6};
		int[] e = {-6,-3,-3,8,-5,-4};
		int[] f = {0,0,0,0,0,0,0,0,0,0,1,1,1,-2,-1};
		int[] g = {3,1,2,3,0};
		System.out.println(isMadhavArray(a));
		System.out.println(isMadhavArray(b));
		System.out.println(isMadhavArray(c));
		System.out.println(isMadhavArray(e));
		System.out.println(isMadhavArray(f));
		System.out.println(isMadhavArray(g));
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
			if(!array[k]) count++;
		}
		return count;
	}
	
//	Test2:
	public static int isMadhavArray(int[ ] a){
		int isMadArray = 0;
		//注意要考虑边界问题
//		for(int sum=a[0],i=2,length=3;1==1;i++,length=i*(i+1)/2){
//			if(a.length<length){
//				isMadArray = 0;
//				return isMadArray;
//			}else{
//				int rightSum = 0;
//				if(sum !=a[i-1]+a[i]){
//					return isMadArray;
//				}
//			}
//			
//		}
		if(a.length<3){
			return isMadArray;
		}
		for(int j=1,i=1,rightSum=0,sum=a[0];j<a.length;j=j+i,i++){
			
		}
		isMadArray =1;
		return isMadArray;
	}
	
	
}
