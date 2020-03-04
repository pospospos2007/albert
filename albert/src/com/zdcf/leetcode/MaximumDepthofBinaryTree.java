package com.zdcf.leetcode;
//104. Maximum Depth of Binary Tree
//Given a binary tree, find its maximum depth.
//
//The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
public class MaximumDepthofBinaryTree {
	 public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
	 }
	public int maxDepth(TreeNode root) {
        int left =0;
        int right = 0;
        if(null!=root){
            left++;
            right++;
        }else{
            return 0;
        }
        return Math.max(calSum(root.left,left),calSum(root.right,right));
    }
    private int  calSum(TreeNode temp,int count){
        if(null==temp){
            return count;
        }else{
            return Math.max(calSum(temp.left,count+1),calSum(temp.right,count+1));
        }
    }
    
}
