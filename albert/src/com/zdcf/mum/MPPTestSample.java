package com.zdcf.mum;

public class MPPTestSample {
	static int getsum(int n) {
		   return n == 0 ? 0 : n % 10 + getsum(n/10);
		}
	// Returns sum of first n natural numbers
    public static int recurSum(int n)
    {
        if (n <= 1)
            return n;
        return n + recurSum(n - 1);
    }
     
	public static void main(String args[]){
//		int sum = 0;
//		for(int i=1;i<=5;i++){
//			sum+=getsum(i);
//		}
//		System.out.println(sum+"");
		System.out.println(recurSum(5));
	}

}
