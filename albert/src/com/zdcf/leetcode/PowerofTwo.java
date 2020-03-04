package com.zdcf.leetcode;

public class PowerofTwo {
	public int isPowerOfTwo(int n) {
		return  (n&(n-1));
	}
	public static void main(String[] args) {
		PowerofTwo p =new PowerofTwo();
		System.out.println(p.isPowerOfTwo(8));
	}
}
