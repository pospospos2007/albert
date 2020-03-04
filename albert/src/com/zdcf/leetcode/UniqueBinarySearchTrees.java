package com.zdcf.leetcode;
//96. Unique Binary Search Trees
//Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
//
//        Example:
//
//        Input: 3
//        Output: 5
//        Explanation:
//        Given n = 3, there are a total of 5 unique BST's:
//
//        1         3     3      2      1
//        \       /     /      / \      \
//        3     2     1      1   3      2
//        /     /       \                 \
//        2     1         2                 3
public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        // Ct+1 += Ci*Ct-i (0<= i <=t)
        if(n==0||n==1){
            return 1;
        }
        int[] C = new int[n+1];
        C[0] = 1;
        for(int t=1;t<=n;t++){
            for(int i=0;i<=t-1;i++){
                C[t]  += C[i]*C[t-1-i];
            }
        }
        return C[n];
    }
}
